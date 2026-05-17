package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.entity.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TrainerMapperTest {

    private TrainerMapper trainerMapper;

    @BeforeEach
    void setUp() {
        trainerMapper = new TrainerMapperImpl();
    }

    @Test
    public void toEntityTest() {
        CreateTrainerDto createTrainerDto = new CreateTrainerDto();
        createTrainerDto.setFirstName("Alex");
        createTrainerDto.setLastName("Kervy");
        createTrainerDto.setEmail("a.@mail.com");
        createTrainerDto.setPhoneNumber("+79995555555");
        createTrainerDto.setSpecialization("Бокс");

        Trainer trainer = trainerMapper.toEntity(createTrainerDto);

        assertNotNull(trainer);
        assertEquals(0, trainer.getId());
        assertEquals("Alex", trainer.getFirstName());
        assertEquals("Kervy", trainer.getLastName());
        assertEquals("a.@mail.com", trainer.getEmail());
        assertEquals("+79995555555", trainer.getPhone());
        assertEquals("Бокс", trainer.getSpecialization());
        assertNotNull(trainer.getTrainingSessions());
    }

    @Test
    public void toDtoTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1);
        trainer.setFirstName("Alex");
        trainer.setLastName("Kervy");
        trainer.setEmail("a.@mail.com");
        trainer.setPhone("+79995555555");
        trainer.setSpecialization("Бокс");

        TrainerResponseDto trainerResponseDto = trainerMapper.toDto(trainer);

        assertNotNull(trainerResponseDto);
        assertEquals(1, trainerResponseDto.getId());
        assertEquals("Alex", trainerResponseDto.getFirstName());
        assertEquals("Kervy", trainerResponseDto.getLastName());
        assertEquals("a.@mail.com", trainerResponseDto.getEmail());
        assertEquals("+79995555555", trainerResponseDto.getPhoneNumber());
        assertEquals("Бокс", trainerResponseDto.getSpecialization());
    }


}
