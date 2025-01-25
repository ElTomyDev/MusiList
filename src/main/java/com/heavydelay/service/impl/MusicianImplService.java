package com.heavydelay.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.musician.MusicianReturnDto;
import com.heavydelay.model.dto.musician.MusicianUpdateDto;
import com.heavydelay.model.entity.Musician;
import com.heavydelay.repository.BandRepository;
import com.heavydelay.repository.MusicianRepository;
import com.heavydelay.repository.RoleRepository;
import com.heavydelay.repository.UserRepository;
import com.heavydelay.service.IMusician;

@Service
public class MusicianImplService implements IMusician{

    private MusicianRepository musicianRepository;
    private RoleRepository roleRepository;
    private BandRepository bandRepository;
    private UserRepository userRepository;

    public MusicianImplService(MusicianRepository musicianRepository, RoleRepository roleRepository, BandRepository bandRepository, UserRepository userRepository){
        this.musicianRepository = musicianRepository;
        this.roleRepository = roleRepository;
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MusicianReturnDto changeMusicianAdminById(Long id, MusicianUpdateDto dto) {
        Musician updateMusician = musicianRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The musician with ID '" + id + "' not found")
        );

        updateMusician.setAdmin(dto.isAdmin());

        musicianRepository.save(updateMusician);

        return MusicianReturnDto.toBasicDto(updateMusician);
    }

    @Override
    public MusicianReturnDto changeMusicianRoleById(Long id, MusicianUpdateDto dto) {
        Musician updateMusician = musicianRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The musician with ID '" + id + "' not found")
        );

        updateMusician.setRole(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
            () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' not found")
        ));

        musicianRepository.save(updateMusician);

        return MusicianReturnDto.toBasicDto(updateMusician);
    }

    @Override
    public MusicianReturnDto createNewMusician(MusicianUpdateDto dto) {
        Musician newMusician = Musician.builder()
                               .user(userRepository.findById(dto.getIdUser()).orElseThrow( // Si el usuario no existe
                                    () -> new ResourceNotFoundException("The user with ID '" + dto.getIdUser() + "' not found")
                                ))
                               .band(bandRepository.findById(dto.getIdBand()).orElseThrow( // Si la banda no existe
                                    () -> new ResourceNotFoundException("The band with ID '" + dto.getIdBand() + "' not found")
                                ))
                               .isAdmin(dto.isAdmin())
                               .role(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow( // Si el rol no existe
                                    () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' not found")
                                )).build();
        musicianRepository.save(newMusician);
        return MusicianReturnDto.toBasicDto(newMusician);
    }

    @Override
    public void deleteMusicianById(Long id) {
        Musician musician = musicianRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The musician with ID '" + id + "' not found")
        );
        musicianRepository.delete(musician);
    }

    @Override
    public List<MusicianReturnDto> showAllMusicians(boolean detailed) {
        List<Musician> musicians = (List<Musician>) musicianRepository.findAll();

        Function<Musician, MusicianReturnDto> mapper = detailed ? MusicianReturnDto::toDetailedDto : MusicianReturnDto::toBasicDto;

        return musicians.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public MusicianReturnDto showMusicianById(Long id, boolean detailed) {
        Musician musician = musicianRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The musician with ID '" + id + "' not found")
        );

        return detailed ? MusicianReturnDto.toDetailedDto(musician) : MusicianReturnDto.toBasicDto(musician);
    }
    
}
