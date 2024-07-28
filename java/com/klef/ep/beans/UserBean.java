package com.klef.ep.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.RejectedBooks;
import com.klef.ep.models.User;
import com.klef.ep.services.UserService;

@ManagedBean(name = "userbean", eager = true)
@SessionScoped
public class UserBean {
    @EJB(lookup = "java:global/LMSPROJ/UserServiceImpl!com.klef.ep.services.UserService")
    UserService service;

    private User user;
    private int id;
    private String name;
    private String gender;
    private String email;
    private String password;
    private String contact;
    private String approved;
    private List<BookIssue> reqlist;
    private List<RejectedBooks> rejlist;
    private List<Book> booklist;
    private List<Book> filteredBookList; // Filtered list of books
    private List<String> categories; // List of all categories
    private List<String> authors; // List of all authors
    private String searchQuery; // Combined search query
    private List<String> selectedCategories = new ArrayList<>();
    private List<String> selectedAuthors = new ArrayList<>();

    @PostConstruct
    public void init() {
        booklist = service.ViewAllBooks();
        filteredBookList = new ArrayList<>(booklist);
    }

    public List<BookIssue> getReqlist() {
        return service.viewreqbooks(user.getName());
    }

    public void setReqlist(List<BookIssue> reqlist) {
        this.reqlist = reqlist;
    }

    public List<RejectedBooks> getRejlist() {
        return service.viewrejbooks(user.getName());
    }

    public void setRejlist(List<RejectedBooks> rejlist) {
        this.rejlist = rejlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<Book> booklist) {
        this.booklist = booklist;
        filterBooks(); // Initialize filtered list with current book list
    }

    public List<Book> getFilteredBookList() {
        return filteredBookList;
    }

    public void setFilteredBookList(List<Book> filteredBookList) {
        this.filteredBookList = filteredBookList;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        filterBooks();
    }

    public List<String> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<String> selectedCategories) {
        this.selectedCategories = selectedCategories;
        filterBooks();
    }

    public List<String> getSelectedAuthors() {
        return selectedAuthors;
    }

    public void setSelectedAuthors(List<String> selectedAuthors) {
        this.selectedAuthors = selectedAuthors;
        filterBooks();
    }

    public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String add() {
        User u = new User();
        u.setName(name);
        u.setId(id);
        u.setGender(gender);
        u.setEmail(email);
        u.setPassword(password);
        u.setContact(contact);
        u.setApproved("Pending");

        service.adduser(u);

        return "usersucc.jsf";
    }

    public void validateuserlogin() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        User user = service.checkuserlogin(email, password);

        if(user==null)
 	   {
 		   response.sendRedirect("requestfail.html");
 	   }
 	   else if(user!=null && "Pending".equals(user.getApproved()))
 	   {
 			   response.sendRedirect("pendingreq.html");
 		   
 	   }
 	   else {
 		   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", user);
            this.user = user;
            FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
 	   }
    }
    

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index.jsf";
    }

    // Filter books based on search and selected filters
    private void filterBooks() {
        filteredBookList = booklist.stream()
            .filter(book -> (searchQuery == null || searchQuery.isEmpty() || 
                book.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                book.getCategory().toLowerCase().contains(searchQuery.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase()) ||
                book.getDescription().toLowerCase().contains(searchQuery.toLowerCase())) &&
                (selectedCategories.isEmpty() || selectedCategories.contains(book.getCategory())) &&
                (selectedAuthors.isEmpty() || selectedAuthors.contains(book.getAuthor())))
            .collect(Collectors.toList());
    }

}