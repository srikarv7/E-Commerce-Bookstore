package com.me.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.persistence.Transient;



@Entity
@Table(name="adverttable")
public class Advert {


	@Id
	@Column(name="ADVERT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Basic
	@Column(name="ADVERT_TITLE")
    private String title;
	
	@Basic
	@Column(name="ADVERT_MESSAGE")
    private String message;
    
	@Transient
    private String category;
    
    @ManyToOne
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Advert_Category",
	joinColumns = { @JoinColumn(name = "fk_advert") },
	inverseJoinColumns = { @JoinColumn(name = "fk_category") })
	private List<Category> categories = new ArrayList<Category>();
    
    // private Set<String> images = new HashSet<String>
    // private List<String> images = new ArrayList<String>
    // private Map<String, String> images = new HashMap<String, String>
    // private SortedMap<String, String> images = new TreeSets<String, String>


	public Advert() {
    }
 

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
    
    public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		categories.add( category );
		category.getAdverts().add( this );
	}

	public void removeCategory(Category category) {
		categories.remove( category );
		category.getAdverts().remove( this );
	}

    public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
}