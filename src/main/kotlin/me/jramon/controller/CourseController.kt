package me.jramon.controller

import me.jramon.model.Course
import me.jramon.repository.CourseRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/courses")
class CourseController(private val courseRepository: CourseRepository) {

    @GetMapping(value = ["/{id}"])
    fun find(@PathVariable id: Long): Optional<ResponseEntity<Course>>? = courseRepository.findById(id).map { course ->
        ResponseEntity.ok(course)
    }

    @GetMapping
    fun find(): Optional<ResponseEntity<List<Course>>>? {
        val courses = courseRepository.findAll()
        return Optional.of(ResponseEntity.ok(courses))
    }

    @PostMapping
    fun post(body: Course): Optional<ResponseEntity<Course>> = Optional.of(ResponseEntity.ok(courseRepository.save(body)))

    @PutMapping
    fun put(id: Long, body: Course): Optional<ResponseEntity<Course>>? = courseRepository.findById(id).map { course ->
        ResponseEntity.ok(courseRepository.save(course.copy(id = body.id, title = body.title, description = body.description)))
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long): Optional<Unit>? = courseRepository.findById(id).map { course -> courseRepository.delete(course) }

}