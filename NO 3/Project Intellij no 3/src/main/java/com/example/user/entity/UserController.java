package com.example.user.entity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    // Simpan daftar pengguna dalam koleksi sementara untuk contoh
    private List<User> users = new ArrayList<>();
    private Long nextUserId = 1L; // Untuk menghasilkan ID pengguna baru

    // Endpoint untuk menambahkan pengguna
    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        user.setId(nextUserId++);
        users.add(user);
        return user;
    }

    // Endpoint untuk mengupdate pengguna
    @PostMapping("/update-user")
    public User updateUser(@RequestBody User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(updatedUser.getId())) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        return null; // Pengguna tidak ditemukan
    }

    // Endpoint untuk mendapatkan semua pengguna
    @GetMapping("/get-all-user")
    public List<User> getAllUsers() {
        return users;
    }

    // Endpoint untuk mendapatkan pengguna berdasarkan ID
    @GetMapping("/get-user/{id}")
    public User getUserById(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null; // Pengguna tidak ditemukan
    }
}
