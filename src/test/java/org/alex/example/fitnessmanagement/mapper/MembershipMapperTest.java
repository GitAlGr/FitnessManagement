package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.alex.example.fitnessmanagement.entity.Membership;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MembershipMapperTest {

    private final MembershipMapper membershipMapper = new MembershipMapperImpl();

    @Test
    public void toEntityTest() {
        CreateMembershipDto createMembershipDto = new CreateMembershipDto();
        createMembershipDto.setClientId(1L);
        createMembershipDto.setType(TypeOfMembership.SINGLE);

        Membership membership = membershipMapper.toEntity(createMembershipDto);

        assertNotNull(membership);
        assertEquals(0, membership.getId());
        assertNull(membership.getClient());
        assertEquals(TypeOfMembership.SINGLE, membership.getType());
        assertNotNull(membership.getPurchaseDate());
        assertNotNull(membership.getStartDate());
        assertTrue(membership.isActive());
    }

    @Test
    public void toDtoTest() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Иван");
        client.setLastName("Петров");

        Membership membership = new Membership();
        membership.setId(1);
        membership.setClient(client);
        membership.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto dto = membershipMapper.toDto(membership);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(TypeOfMembership.SINGLE, dto.getType());
        assertEquals(1L, dto.getClientId());
        assertEquals("Иван Петров", dto.getClientFullName());
    }
}
