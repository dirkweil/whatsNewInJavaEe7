package de.gedoplan.demo.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = Publisher.TABLE_NAME)
@NamedEntityGraph(
    name = "Publisher_books",
    attributeNodes = @NamedAttributeNode(value = "books"))
public class Publisher
{
  public static final String TABLE_NAME      = "JPA_PUBLISHER";
  @Id
  @GeneratedValue
  private Integer            id;
  private String             name;
  @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  private List<Book>         books;

  @Transient
  PersistenceUtil            persistenceUtil = Persistence.getPersistenceUtil();

  protected Publisher()
  {
  }

  public Publisher(String name)
  {
    this.name = name;

    this.books = new ArrayList<>();
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  void addBook(Book book)
  {
    this.books.add(book);
  }

  void removeBook(Book book)
  {
    this.books.remove(book);
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    Publisher other = (Publisher) obj;
    if (this.id == null)
    {
      return false;
    }
    else if (!this.id.equals(other.id))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "Publisher [id=" + this.id + ", name=" + this.name + "]";
  }

  public String getBooksAsString()
  {
    if (this.persistenceUtil.isLoaded(this, "books"))
    {
      StringBuilder sb = new StringBuilder();
      for (Book book : this.books)
      {
        if (sb.length() != 0)
        {
          sb.append(", ");
        }
        sb.append(book.getName());
      }
      return sb.toString();
    }

    return "(not loaded)";
  }
}
