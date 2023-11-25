package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    private Integer id;
    private String name;

    private String abbreviation;

    public Department(DepartmentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.abbreviation = builder.abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Department{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", abbreviation='").append(abbreviation).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Ideally this DepartmentBuilder
    public static class DepartmentBuilder {
        private Integer id;
        private String name;
        private String abbreviation;

        public DepartmentBuilder(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public DepartmentBuilder setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }
}


