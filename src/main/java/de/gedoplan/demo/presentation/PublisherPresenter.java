package de.gedoplan.demo.presentation;

import de.gedoplan.demo.persistence.Book;
import de.gedoplan.demo.persistence.Publisher;
import de.gedoplan.demo.persistence.PublisherRepository;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PublisherPresenter
{
  @Inject
  PublisherRepository     publisherRepository;

  private List<Publisher> publishers;

  public List<Publisher> getPublishers()
  {
    return this.publishers;
  }

  public void findAll()
  {
    this.publishers = this.publisherRepository.findAll();
    if (this.publishers.isEmpty())
    {
      createSomePublishersAndBooks();
      this.publishers = this.publisherRepository.findAll();
    }
  }

  public void findAllWithBooks()
  {
    this.publishers = this.publisherRepository.findAll("Publisher_books");
    if (this.publishers.isEmpty())
    {
      createSomePublishersAndBooks();
      this.publishers = this.publisherRepository.findAll("Publisher_books");
    }
  }

  public void createSomePublishersAndBooks()
  {
    Publisher testPublisher1 = new Publisher("O'Melly Publishing");
    Book testBook11 = new Book("Better JPA Programs", "12345-6789-0", 340);
    testBook11.setPublisher(testPublisher1);
    Book testBook12 = new Book("Inside JPA", "54321-9876-X", 265);
    testBook12.setPublisher(testPublisher1);
    Book testBook13 = new Book("Java and Databases", "11111-2222-6", 850);
    testBook13.setPublisher(testPublisher1);
    this.publisherRepository.save(testPublisher1);

    Publisher testPublisher2 = new Publisher("Expert Press");
    Book testBook21 = new Book("Java for Beginners", "564534-432-2", 735);
    testBook21.setPublisher(testPublisher2);
    Book testBook22 = new Book("Java vs. C#", "333333-123-0", 145);
    testBook22.setPublisher(testPublisher2);
    Book testBook23 = new Book("Optimizing Java Programs", "765432-767-8", 230);
    testBook23.setPublisher(testPublisher2);
    this.publisherRepository.save(testPublisher2);
  }

}
