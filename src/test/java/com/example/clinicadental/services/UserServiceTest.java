package com.example.clinicadental.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.clinicadental.entities.User;
import com.example.clinicadental.entities.Role;
import com.example.clinicadental.repositories.UserRepository;
import com.example.clinicadental.repositories.RoleRepository;
import com.example.clinicadental.services.Implementaciones.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder; // Mock PasswordEncoder

    @InjectMocks
    private UserServiceImpl userService; // Usar una implementacion concreta

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Crear un objeto User y asignar nombre de usuario y contraseña
        User user = new User();
        user.setUsername("roberto");
        user.setPassword("password");

        // Crear un objeto Role y asignar nombre de rol, simula el comportamiento real de la aplicacion  y asegura que el metodo save
        // maneje bien la asignacion de roles a los usuarios
        Role role = new Role();
        role.setName("USER");

        // Configurar el comportamiento del mock de userRepository para que devuelva el objeto User cuando se llame al método save
        when(userRepository.save(any(User.class))).thenReturn(user);
        // Configurar el comportamiento del mock de roleRepository para que devuelva un Optional que contiene el objeto Role cuando se llame al método findByName
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));
        // Configurar el comportamiento del mock de passwordEncoder para que devuelva una contraseña codificada cuando se llame al método encode
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        // Llamar al método save de userService con el objeto User
        User savedUser = userService.save(user);

        // Verificar que el objeto savedUser no sea nulo
        assertNotNull(savedUser);
        // Verificar que el nombre de usuario del objeto savedUser sea "roberto"
        assertEquals("roberto", savedUser.getUsername());
        // Verificar que el método save de userRepository se haya llamado exactamente una vez con el objeto User
        verify(userRepository, times(1)).save(user);
    }

    // Guardar un usuario con un rol existente
    @Test
    public void testSaveUserWithExistingRole() {
        User user = new User();
        user.setUsername("andrea");
        user.setPassword("password");

        Role role = new Role();
        role.setName("ADMIN");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("andrea", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    // Guardar un usuario con un rol inexistente
    @Test
    public void testSaveUserWithNonExistingRole() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");

        when(roleRepository.findByName("NON_EXISTENT_ROLE")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("john", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    // Guardar un usuario con una contraseña codificada
    @Test
    public void testSaveUserWithEncodedPassword() {
        User user = new User();
        user.setUsername("maria");
        user.setPassword("password");

        Role role = new Role();
        role.setName("USER");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    // Verificar existencia de un usuario por nombre de usuario
    @Test
    public void testExistsByUsername() {
        when(userRepository.existsByUsername("roberto")).thenReturn(true);

        boolean exists = userService.existsByUsername("roberto");

        assertTrue(exists);
        verify(userRepository, times(1)).existsByUsername("roberto");
    }

    // Obtener todos los usuarios
    @Test
    public void testFindAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}