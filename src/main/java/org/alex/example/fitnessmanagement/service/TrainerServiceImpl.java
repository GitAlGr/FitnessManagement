package org.alex.example.fitnessmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainerDto;
import org.alex.example.fitnessmanagement.entity.Trainer;
import org.alex.example.fitnessmanagement.error.*;
import org.alex.example.fitnessmanagement.mapper.TrainerMapper;
import org.alex.example.fitnessmanagement.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @Override
    public void add(CreateTrainerDto createTrainerDto) {
        log.info("Добавление тренера в базу: {}", createTrainerDto);
        if (trainerRepository.existsByEmail(createTrainerDto.getEmail())) {
            throw new BusinessFitnessException("Тренер с данным email уже существует");
        }
        if (createTrainerDto.getPhoneNumber() != null && trainerRepository.existsByPhone(createTrainerDto.getPhoneNumber())) {
            throw new BusinessFitnessException("Тренер с данным номером телефона уже существует");
        }
        Trainer trainer = trainerMapper.toEntity(createTrainerDto);
        trainerRepository.save(trainer);

    }

    @Override
    public TrainerResponseDto findById(long id) {
        log.info("Поиск тренера по id: {}", id);
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new NotFoundByIdException("Тренера с данным id не существует"));
        return trainerMapper.toDto(trainer);
    }

    @Override
    public TrainerResponseDto findByFirstName(String firstName) {
        log.info("Поиск тренера по имени: {}", firstName);
        Trainer trainer = trainerRepository.findByFirstName(firstName).orElseThrow(() -> new NotFoundByNameException("Тренера с данным именем не существует"));
        return trainerMapper.toDto(trainer);
    }

    @Override
    public TrainerResponseDto findByLastName(String lastName) {
        log.info("Поиск тренера по фамилии: {}", lastName);
        Trainer trainer = trainerRepository.findByLastName(lastName).orElseThrow(() -> new NotFoundByNameException("Тренера с данной фамилией не существует"));
        return trainerMapper.toDto(trainer);
    }

    @Override
    public TrainerResponseDto findByEmail(String email) {
        log.info("Поиск тренера по email: {}", email);
        Trainer trainer = trainerRepository.findByEmail(email).orElseThrow(() -> new NotFoundByEmailException("Тренера с данным email не существует"));
        return trainerMapper.toDto(trainer);
    }

    @Override
    public TrainerResponseDto findByPhone(String phone) {
        log.info("Поиск тренера по номеру телефона: {}", phone);
        Trainer trainer = trainerRepository.findByPhone(phone).orElseThrow(() -> new NotFoundByPhoneNumberException("Тренера с данным номером телефона не существует"));
        return trainerMapper.toDto(trainer);
    }

    @Override
    public List<TrainerResponseDto> findBySpecialization(String specialization) {
        log.info("Поиск тренеров по специальности: {}", specialization);
        List<Trainer> trainer = trainerRepository.findBySpecialization(specialization);
        return trainerMapper.toDtoList(trainer);
    }

    @Override
    public List<TrainerResponseDto> findByActiveTrue() {
        log.info("Поиск активных тренеров");
        List<Trainer> trainer = trainerRepository.findByActiveTrue();
        return trainerMapper.toDtoList(trainer);
    }

    @Override
    public List<TrainerResponseDto> findByActiveFalse() {
        log.info("Поиск неактивных тренеров");
        List<Trainer> trainer = trainerRepository.findByActiveFalse();
        return trainerMapper.toDtoList(trainer);
    }

    @Override
    public List<TrainerResponseDto> showAllTrainers() {
        log.info("Вывести список всех тренеров");
        List<Trainer> trainer = trainerRepository.findAll();
        return trainerMapper.toDtoList(trainer);
    }

    @Override
    public void updateInfoAboutTrainer(long trainerId, UpdateTrainerDto updateTrainerDto) {
        log.info("Обновление данных тренера по его id: {} {} ", trainerId, updateTrainerDto);
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NotFoundByIdException("Тренера с данным id не существует"));
        if (updateTrainerDto.getFirstName() != null) {
            trainer.setFirstName(updateTrainerDto.getFirstName());
        }
        if (updateTrainerDto.getLastName() != null) {
            trainer.setLastName(updateTrainerDto.getLastName());
        }
        if (updateTrainerDto.getPhoneNumber() != null) {
            trainer.setPhone(updateTrainerDto.getPhoneNumber());
        }
        if (updateTrainerDto.getActive() != null) {
            trainer.setActive(updateTrainerDto.getActive());
        }
        trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(long trainerId) {
        log.info("Удаление тренера из базы по id: {}", trainerId);
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NotFoundByIdException("Тренера с данным id не существует"));
        trainerRepository.delete(trainer);
    }
}
