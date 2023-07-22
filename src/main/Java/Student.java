

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @ManyToMany(cascade ={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "students_tracks", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "track_id")})
   @Fetch(FetchMode.JOIN)

    private List<Track> tracksList;


    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getTracksAsCsv() {
        if (tracksList != null && !tracksList.isEmpty()) {
            return getTracksList().stream().map(Track::getName).collect(Collectors.joining(", "));
        }
        return "Is empty";
    }


}
