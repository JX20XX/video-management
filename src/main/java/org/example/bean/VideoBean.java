package org.example.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.example.constant.VideoStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "video_table")
//@EntityListeners(value = AuditingEntityListener.class)
public class VideoBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "video title is mandatory")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "video description is mandatory")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "upload_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime uploadTimestamp;

    @NotNull(message = "video duration is mandatory")
    @Column(name = "duration")
    private Long duration;

    @NotBlank(message = "video resolution is mandatory")
    @Column(name = "resolution")
    private String resolution;

    @NotBlank(message = "video format is mandatory")
    @Column(name = "format")
    private String format;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private VideoStatus status;
}
