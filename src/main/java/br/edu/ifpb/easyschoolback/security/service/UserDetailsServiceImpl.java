package br.edu.ifpb.easyschoolback.security.service;

import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final var student = studentRepository.findByEmail(email);

        if (student.isPresent()) {
            final var createAuthorityList = AuthorityUtils.createAuthorityList(student.get().getRole());
            return UserDetailsImpl.build(student.get(), createAuthorityList);
        }

        final var teacher = teacherRepository.findByEmail(email);

        if (teacher.isPresent()) {
            final var createAuthorityList = AuthorityUtils.createAuthorityList(teacher.get().getRole());
            return UserDetailsImpl.build(teacher.get(), createAuthorityList);
        }

        throw new UsernameNotFoundException("User not found!");
    }

}


