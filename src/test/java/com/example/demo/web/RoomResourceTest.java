package com.example.demo.web;

import com.example.demo.config.security.*;
import com.example.demo.domain.Room;
import com.example.demo.enums.RoomType;
import com.example.demo.enums.SearchType;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.service.RoomService;
import com.example.demo.service.dto.*;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.JwtUtil;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RoomResource.class)
@ActiveProfiles("test")
class RoomResourceTest {

  private MockMvc mockMvc;

  @Value("${jwt.key}")
  private String key;

  @Value("${user.id}")
  private String id;

  @Value("${user.email}")
  private String email;

  @Value("${user.password}")
  private String password;

  @MockBean private RoomService roomService;

  @MockBean private SuccessHandler authenticationSuccessHandler;

  @MockBean private FailHandler authenticationFailureHandler;

  @MockBean private AuthenticationManager authenticationManager;

  private String token;

  @BeforeEach
  void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new RoomResource(roomService))
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .addFilters(new JwtExceptionFilter())
            .addFilters(new JwtValidFilter(key))
            .addFilters(
                new JwtLoginFilter(
                    "/login",
                    authenticationManager,
                    authenticationSuccessHandler,
                    authenticationFailureHandler))
            .build();

    token = JwtUtil.createAccessToken(key, Long.valueOf(id), email);
  }

  @Test
  @DisplayName("내방 등록 로직")
  void createdRoom() throws Exception {

    Room mock = RoomMock.getMock(UserMock.getMock());

    RoomDTO dto = RoomMock.getRoomDTO();

    BDDMockito.given(roomService.createRoom(any(), any())).willReturn(mock);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/room")
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .content(JsonUtil.convertObjectToJson(dto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().createRoom(any(), any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['id']").value(mock.getId()))
        .andExpect(jsonPath("$['name']").value(mock.getName()))
        .andExpect(jsonPath("$['type']").value(mock.getType().name()))
        .andExpect(jsonPath("$['user']['email']").value(mock.getUser().getEmail()));
  }

  @Test
  @DisplayName("방제거 테스트 케이스")
  void removeRoom() throws Exception {

    BDDMockito.willDoNothing().given(roomService).removeOne(any());

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.delete("/api/room/" + id)
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().removeOne(any());

    action.andExpect(status().isOk());
  }

  @Test
  @DisplayName("내방 하나 불러오기")
  void getRoom() throws Exception {

    Room mock = RoomMock.getMock(UserMock.getMock());

    BDDMockito.given(roomService.getOne(any())).willReturn(mock);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/room/" + id)
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().getOne(any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['id']").value(mock.getId()))
        .andExpect(jsonPath("$['name']").value(mock.getName()))
        .andExpect(jsonPath("$['type']").value(mock.getType().name()));
  }

  @Test
  @DisplayName("하나의 방 업데이트")
  void updateRoom() throws Exception {

    Room mock = RoomMock.getMock(UserMock.getMock());

    RoomDTO dto = RoomMock.getRoomDTO();

    BDDMockito.given(roomService.updateOne(any(), any())).willReturn(mock);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.put("/api/room/" + id)
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .content(JsonUtil.convertObjectToJson(dto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().updateOne(any(), any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['id']").value(mock.getId()))
        .andExpect(jsonPath("$['name']").value(mock.getName()))
        .andExpect(jsonPath("$['type']").value(mock.getType().name()));
  }

  @Test
  @DisplayName("나의 방 리스트업")
  void getMyRooms() throws Exception {

    Page<RoomInfo> mocks = RoomMock.getRoomInfo(UserMock.getMock());

    BDDMockito.given(roomService.getMyRoomList(any(), any())).willReturn(mocks);

    PageDTO dto = RoomMock.getPageDTO();

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/my-rooms")
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .param("page", dto.getPage().toString())
                    .param("size", dto.getSize().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().getMyRoomList(any(), any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['content'][0].id").value(mocks.getContent().get(0).getId()))
        .andExpect(jsonPath("$['content'][0].name").value(mocks.getContent().get(0).getName()))
        .andExpect(
            jsonPath("$['content'][0].type").value(mocks.getContent().get(0).getType().name()))
        .andExpect(
            jsonPath("$['content'][0].dealSet.size()")
                .value(mocks.getContent().get(0).getDealSet().size()));
  }

  @Test
  @DisplayName("전체 조회 목록 API")
  void getAllRooms() throws Exception {

    SearchDTO dto = SearchDTO.builder().roomType(RoomType.ONE).type(SearchType.ROOM).build();

    PageImpl<RoomInfoDTO> mocks = RoomMock.getPageMocks();

    BDDMockito.given(roomService.getAllRoomList(any())).willReturn(mocks);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/rooms")
                    .header(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
                    .param("page", dto.getPage().toString())
                    .param("size", dto.getSize().toString())
                    .param("roomType", dto.getRoomType().name())
                    .param("type", dto.getType().name())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(roomService).should().getAllRoomList(any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['content'][0].id").value(mocks.getContent().get(0).getId()))
        .andExpect(jsonPath("$['content'][0].name").value(mocks.getContent().get(0).getName()))
        .andExpect(
            jsonPath("$['content'][0].type").value(mocks.getContent().get(0).getType().name()));
  }
}
