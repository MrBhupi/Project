package com.project.Project.repository;

import com.project.Project.model.Users;
import com.project.Project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
<<<<<<< HEAD

    Optional<Users> findByEmail(String email);

    List<Users> findByRole(Role role);


    List<Users> findByRoleIn(List<Role> roles);
=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
}
