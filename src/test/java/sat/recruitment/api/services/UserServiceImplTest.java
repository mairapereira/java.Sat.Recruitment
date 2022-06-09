package sat.recruitment.api.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.repositories.UserDAO;
import sat.recruitment.api.services.domain.constants.UserType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static platform.messagingservice.factory.UserMockFactory.mockNormalUser;
import static sat.recruitment.api.services.domain.constants.Errors.USER_DUPLICATE;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Test
    public void createUserWhenDataOkExpectsOK() {
        var request = mockNormalUser();
        when(userDAO.findUsersBy(any(), any(), any(), any()))
                .thenReturn(new ArrayList<>());
        when(userDAO.createuser(any()))
                .thenReturn(request);

        var userCreated = userService.createUser(request);

        verify(userDAO).createuser(any());
        assertThat(userCreated.getUserType()).isEqualTo(UserType.NORMAL);

    }

    @Test
    public void createUserWhenDuplicateDataExpectsError() {
        var request = mockNormalUser();
        when(userDAO.findUsersBy(any(), any(), any(), any()))
                .thenReturn(Arrays.asList(request));

        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining(USER_DUPLICATE.getDescription());

    }

}