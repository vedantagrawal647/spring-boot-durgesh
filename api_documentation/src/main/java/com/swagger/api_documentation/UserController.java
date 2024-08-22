package com.swagger.api_documentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "User Management")
public class UserController {

    @ApiOperation(value = "Get list of all users", response = Iterable.class)
    @GetMapping("/users")
    public List<User> getUsers() {
        // Implementation to fetch and return users
        List<User> l =new ArrayList<>();
        l.add(new User("vedant",1));
        return l;
    }

    @ApiOperation(value = "Get user by ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "User ID", required = true) @PathVariable("id") Long id) {
        // Implementation to fetch and return user by ID
        User u = new User("ramanuj",1);
        return ResponseEntity.ok(u);
    }

    @ApiOperation(value = "Create a new user", response = User.class)
    @PostMapping("/users")
    public ResponseEntity<User> createUser(
            @ApiParam(value = "User object to be created", required = true) @RequestBody User user) {
        // Implementation to create a new user
        User u = new User("kartik",3);
        return ResponseEntity.ok(u);
    }

    // Add more endpoints and annotations as needed
}
