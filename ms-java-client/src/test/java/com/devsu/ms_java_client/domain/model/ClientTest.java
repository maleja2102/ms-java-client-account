package com.devsu.ms_java_client.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.devsu.ms_java_client.domain.model.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ClientTest {

    private static Identity createIdentity() {
        return new Identity("John Doe", Gender.MALE, 30, "123456789", "CC");
    }

    private static ContactInformation createContactInformation() {
        return new ContactInformation("123 Main St", "555-1234");
    }

    @Test
    @DisplayName("Client.create should initialize fields without id")
    void createShouldInitializeFieldsWithoutId() {
        Identity identity = createIdentity();
        ContactInformation contactInformation = createContactInformation();

        Client client = Client.create(identity, contactInformation, "secret", true);

        assertNull(client.getClientId());
        assertSame(identity, client.getIdentity());
        assertSame(contactInformation, client.getContactInformation());
        assertEquals("secret", client.getPassword());
        assertTrue(client.isActive());
    }

    @Test
    @DisplayName("Client.create should replace null contact information with empty")
    void createShouldReplaceNullContactInformationWithEmpty() {
        Client client = Client.create(createIdentity(), null, "password", false);

        assertNotNull(client.getContactInformation());
        assertEquals(ContactInformation.empty(), client.getContactInformation());
    }

    @Test
    @DisplayName("Client.assignId should return new client with provided id")
    void assignIdShouldReturnNewClientWithProvidedId() {
        Client original = Client.create(createIdentity(), createContactInformation(), "password", true);

        Client withId = original.assignId(10L);

        assertEquals(10L, withId.getClientId());
        assertNotSame(original, withId);
        assertSame(original.getIdentity(), withId.getIdentity());
        assertSame(original.getContactInformation(), withId.getContactInformation());
        assertEquals(original.getPassword(), withId.getPassword());
        assertEquals(original.isActive(), withId.isActive());
    }

    @Test
    @DisplayName("Client.updateWith should keep id and update remaining fields")
    void updateWithShouldKeepIdAndUpdateFields() {
        Client original = Client.withId(5L, createIdentity(), createContactInformation(), "password", true);
        Identity updatedIdentity = new Identity("Jane Roe", Gender.FEMALE, 28, "987654321", "CC");
        ContactInformation updatedContact = new ContactInformation("456 Elm St", "555-5678");

        Client updated = original.updateWith(updatedIdentity, updatedContact, "newPass", false);

        assertEquals(5L, updated.getClientId());
        assertSame(updatedIdentity, updated.getIdentity());
        assertSame(updatedContact, updated.getContactInformation());
        assertEquals("newPass", updated.getPassword());
        assertFalse(updated.isActive());
    }

    @Nested
    @DisplayName("Client validation")
    class Validation {

        @Test
        @DisplayName("Client.create should require non-null identity")
        void createShouldRequireIdentity() {
            NullPointerException exception = assertThrows(NullPointerException.class,
                    () -> Client.create(null, createContactInformation(), "password", true));

            assertEquals("Identity is required", exception.getMessage());
        }

        @Test
        @DisplayName("Client.create should require non-blank password")
        void createShouldRequireNonBlankPassword() {
            IllegalArgumentException nullPasswordException = assertThrows(IllegalArgumentException.class,
                    () -> Client.create(createIdentity(), createContactInformation(), null, true));
            assertEquals("Password must not be blank", nullPasswordException.getMessage());

            IllegalArgumentException blankPasswordException = assertThrows(IllegalArgumentException.class,
                    () -> Client.create(createIdentity(), createContactInformation(), "   ", true));
            assertEquals("Password must not be blank", blankPasswordException.getMessage());
        }
    }
}
