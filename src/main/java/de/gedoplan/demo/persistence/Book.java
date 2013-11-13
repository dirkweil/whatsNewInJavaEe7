package de.gedoplan.demo.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Book.TABLE_NAME)
public class Book
{
  public static final String TABLE_NAME = "JPA_BOOK";

  @Id
  @GeneratedValue
  private Integer            id;
  private String             name;
  private String             isbn;
  private int                pages;

  @ManyToOne
  private Publisher          publisher;

  protected Book()
  {
  }

  public Book(String name, String isbn, int pages)
  {
    this.name = name;
    this.isbn = isbn;
    this.pages = pages;
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getIsbn()
  {
    return this.isbn;
  }

  public int getPages()
  {
    return this.pages;
  }

  public Publisher getPublisher()
  {
    return this.publisher;
  }

  public void setPublisher(Publisher publisher)
  {
    if (this.publisher != null)
    {
      this.publisher.removeBook(this);
    }

    this.publisher = publisher;

    if (this.publisher != null)
    {
      this.publisher.addBook(this);
    }
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
    Book other = (Book) obj;
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
    return "Book [id=" + this.id + ", name=" + this.name + ", isbn=" + this.isbn + ", pages=" + this.pages + "]";
  }

}
