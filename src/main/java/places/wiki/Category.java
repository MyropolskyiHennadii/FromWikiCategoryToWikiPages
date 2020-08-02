package places.wiki;

public class Category implements Comparable {
    private String lang;
    private String name;
    private String reference;

    public Category(String lang, String name, String reference) {
        this.lang = lang;
        this.reference = reference;
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Category) o).getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getReference().equals(category.getReference());
    }

    @Override
    public int hashCode() {
        return getReference().length();
    }

    @Override
    public String toString() {
        return "Category{" +
                "lang='" + lang + '\'' +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
