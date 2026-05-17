package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainerDto;
import org.alex.example.fitnessmanagement.entity.Trainer;
import org.alex.example.fitnessmanagement.mapper.TrainerMapper;
import org.alex.example.fitnessmanagement.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerMapper trainerMapper;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @Test
    public void addTrainerTest() {
        CreateTrainerDto createTrainerDto = new CreateTrainerDto();
        createTrainerDto.setFirstName("Alex");
        createTrainerDto.setLastName("Kervy");
        createTrainerDto.setPhoneNumber("79995556644");
        createTrainerDto.setEmail("tr@mail.com");
        createTrainerDto.setSpecialization("Бокс");

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("Alex");
        trainer.setLastName("Kervy");
        trainer.setPhone("79995556644");
        trainer.setEmail("tr@mail.com");
        trainer.setSpecialization("Бокс");

        when(trainerMapper.toEntity(createTrainerDto)).thenReturn(trainer);
        when(trainerRepository.save(trainer)).thenReturn(trainer);

        trainerService.add(createTrainerDto);

        verify(trainerMapper, times(1)).toEntity(createTrainerDto);
        verify(trainerRepository, times(1)).save(trainer);
    }

    @Test
    public void findTrainerByIdTest() {
        long trainerId = 1L;

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(trainerId);

        when(trainerMapper.toDto(trainer)).thenReturn(trainerResponseDto);
        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

        trainerService.findById(trainerId);

        verify(trainerMapper, times(1)).toDto(trainer);
        verify(trainerRepository, times(1)).findById(trainerId);
    }

    @Test
    public void findByFirstNameTest() {
        String firstName = "Alex";

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName(firstName);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setFirstName(firstName);

        when(trainerMapper.toDto(trainer)).thenReturn(trainerResponseDto);
        when(trainerRepository.findByFirstName(firstName)).thenReturn(Optional.of(trainer));

        trainerService.findByFirstName(firstName);

        verify(trainerMapper, times(1)).toDto(trainer);
        verify(trainerRepository, times(1)).findByFirstName(firstName);
    }

    @Test
    public void findLastNameTest() {
        String lastName = "Kervy";

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setLastName(lastName);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setLastName(lastName);

        when(trainerMapper.toDto(trainer)).thenReturn(trainerResponseDto);
        when(trainerRepository.findByLastName(lastName)).thenReturn(Optional.of(trainer));

        trainerService.findByLastName(lastName);

        verify(trainerMapper, times(1)).toDto(trainer);
        verify(trainerRepository, times(1)).findByLastName(lastName);
    }

    @Test
    public void findTrainerByEmailTest() {
        String email = "al@mail.com";

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setEmail(email);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setEmail(email);

        when(trainerMapper.toDto(trainer)).thenReturn(trainerResponseDto);
        when(trainerRepository.findByEmail(email)).thenReturn(Optional.of(trainer));

        trainerService.findByEmail(email);

        verify(trainerMapper, times(1)).toDto(trainer);
        verify(trainerRepository, times(1)).findByEmail(email);
    }

    @Test
    public void findTrainerByPhoneNumberTest() {
        String phone = "79995554466";

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setPhone(phone);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setPhoneNumber(phone);

        when(trainerMapper.toDto(trainer)).thenReturn(trainerResponseDto);
        when(trainerRepository.findByPhone(phone)).thenReturn(Optional.of(trainer));

        trainerService.findByPhone(phone);

        verify(trainerMapper, times(1)).toDto(trainer);
        verify(trainerRepository, times(1)).findByPhone(phone);
    }

    @Test
    public void findTrainerBySpecializationTest() {
        String specialization = "Бокс";

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setSpecialization(specialization);

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);
        trainer2.setSpecialization(specialization);

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        trainers.add(trainer2);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setSpecialization(specialization);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setSpecialization(specialization);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerMapper.toDtoList(trainers)).thenReturn(trainerResponseDtoList);
        when(trainerRepository.findBySpecialization(specialization)).thenReturn(trainers);

        trainerService.findBySpecialization(specialization);

        verify(trainerMapper, times(1)).toDtoList(trainers);
        verify(trainerRepository, times(1)).findBySpecialization(specialization);
    }

    @Test
    public void findActiveTrainersTest() {
        boolean isActive = true;

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setActive(isActive);

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);
        trainer2.setActive(isActive);

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        trainers.add(trainer2);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setActive(isActive);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setActive(isActive);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerMapper.toDtoList(trainers)).thenReturn(trainerResponseDtoList);
        when(trainerRepository.findByActiveTrue()).thenReturn(trainers);

        trainerService.findByActiveTrue();

        verify(trainerMapper, times(1)).toDtoList(trainers);
        verify(trainerRepository, times(1)).findByActiveTrue();
    }

    @Test
    public void findUnactiveTrainersTest() {
        boolean isActive = false;

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setActive(isActive);

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);
        trainer2.setActive(isActive);

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        trainers.add(trainer2);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setActive(isActive);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setActive(isActive);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerMapper.toDtoList(trainers)).thenReturn(trainerResponseDtoList);
        when(trainerRepository.findByActiveFalse()).thenReturn(trainers);

        trainerService.findByActiveFalse();

        verify(trainerMapper, times(1)).toDtoList(trainers);
        verify(trainerRepository, times(1)).findByActiveFalse();
    }

    @Test
    public void findAllTrainersTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        trainers.add(trainer2);

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerMapper.toDtoList(trainers)).thenReturn(trainerResponseDtoList);
        when(trainerRepository.findAll()).thenReturn(trainers);

        trainerService.showAllTrainers();

        verify(trainerMapper, times(1)).toDtoList(trainers);
        verify(trainerRepository, times(1)).findAll();
    }

    @Test
    public void updateTrainerTest() {
        long trainerId = 1L;

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);
        trainer.setFirstName("Alex");
        trainer.setLastName("Kervy");
        trainer.setEmail("al@mail.com");
        trainer.setPhone("79995554466");
        trainer.setSpecialization("Бокс");
        trainer.setActive(true);

        UpdateTrainerDto updateTrainerDto = new UpdateTrainerDto();
        updateTrainerDto.setFirstName("Иван");
        updateTrainerDto.setLastName("Иванов");
        updateTrainerDto.setPhoneNumber("79995554467");
        updateTrainerDto.setSpecialization("Гимнастика");
        updateTrainerDto.setActive(false);

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

        trainerService.updateInfoAboutTrainer(trainerId, updateTrainerDto);

        verify(trainerRepository, times(1)).findById(trainerId);
        verify(trainerRepository, times(1)).save(trainer);
    }

    @Test
    public void deleteTrainerTest() {
        long trainerId = 1L;

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

        trainerService.deleteTrainer(trainerId);

        verify(trainerRepository, times(1)).findById(trainerId);
        verify(trainerRepository, times(1)).delete(trainer);
    }
}
