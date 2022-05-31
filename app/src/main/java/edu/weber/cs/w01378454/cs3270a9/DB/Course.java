package edu.weber.cs.w01378454.cs3270a9.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "id")
    protected String id;
    @ColumnInfo(name = "name")
    protected String name;
    @ColumnInfo(name = "course_name")
    protected String course_name;
    @ColumnInfo(name = "course_code")
    protected String course_code;
    @ColumnInfo(name = "start_at")
    protected String start_at;
    @ColumnInfo(name = "end_at")
    protected String end_at;

    @Ignore
    public Course(String id, String name, String course_name, String course_code, String start_at, String end_at) {

        this.id = id;
        this.name = name;
        this.course_name = course_name;
        this.course_code = course_code;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public Course(int _id,String id, String name,String course_name, String course_code, String start_at, String end_at) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.course_name = course_name;
        this.course_code = course_code;
        this.start_at = start_at;
        this.end_at = end_at;
    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    @Override
    public String toString() {
        return "Course{" +
                "_id=" + _id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_code='" + course_code + '\'' +
                ", start_at='" + start_at + '\'' +
                ", end_at='" + end_at + '\'' +
                '}';
    }

}
