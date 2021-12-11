package si.fri.rso.favourites.services.dtos;

public class ItemDTO {
    private String title;
    private String description;
    private String category;

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
