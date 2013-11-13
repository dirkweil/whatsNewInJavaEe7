package de.gedoplan.demo.interceptor;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor-Implementierung zu {@link TraceCall}.
 * 
 * @author dw
 */
@TraceCall
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TraceCallInterceptor implements Serializable
{
  private static final long serialVersionUID = 1L;

  /**
   * Interceptor-Arbeitsmethode für Konstruktoraufrufe.
   * 
   * @param invocationContext InvocationContext
   * @return Returnwert
   * @throws Exception bei Fehlern
   */
  @AroundConstruct
  public Object traceConstructorCall(InvocationContext invocationContext) throws Exception
  {
    Constructor<?> constructor = invocationContext.getConstructor();
    return traceCall(invocationContext, constructor.getDeclaringClass(), constructor.getName());
  }

  /**
   * Interceptor-Arbeitsmethode für Methodenaufrufe.
   * 
   * @param invocationContext InvocationContext
   * @return Returnwert
   * @throws Exception bei Fehlern
   */
  @AroundInvoke
  public Object traceMethodCall(InvocationContext invocationContext) throws Exception
  {
    Method method = invocationContext.getMethod();
    return traceCall(invocationContext, method.getDeclaringClass(), method.getName());
  }

  private Object traceCall(InvocationContext invocationContext, Class<?> targetClass, String name) throws Exception
  {
    try
    {
      StringBuilder buf = new StringBuilder(name);
      buf.append('(');
      String sep = "";
      Object[] parameters = invocationContext.getParameters();
      if (parameters != null)
      {
        for (Object parm : parameters)
        {
          buf.append(sep + toLoggingString(parm));
          sep = ",";
        }
      }
      buf.append(')');

      // TODO Usage of sysout for demo purposes only; use a logger in real life!
      System.out.println(buf.toString());
    }
    catch (Exception ignore) // CHECKSTYLE:IGNORE Ignorieren von Exceptions ist hier OK
    {
    }

    return invocationContext.proceed();
  }

  private String toLoggingString(Object obj)
  {
    if (obj == null)
    {
      return "null";
    }

    if (obj instanceof Iterable<?>)
    {
      StringBuilder buf = new StringBuilder("[");
      String sep = "";
      for (Object subObj : (Iterable<?>) obj)
      {
        buf.append(sep);
        sep = ",";
        buf.append(toLoggingString(subObj));
      }
      buf.append("]");
      return buf.toString();
    }

    return obj.toString();
  }
}
