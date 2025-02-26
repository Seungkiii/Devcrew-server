package com.example.devcrew.domain.member.api;

import com.example.devcrew.domain.member.dto.request.PostMemberProfileRequest;
import com.example.devcrew.domain.member.dto.request.UpdateCompanyMemberSignUpRequest;
import com.example.devcrew.domain.member.dto.response.GetMemberNameResponse;
import com.example.devcrew.domain.member.dto.response.GetMemberProfileResponse;
import com.example.devcrew.domain.member.dto.response.GetMemberRoleResponse;
import com.example.devcrew.domain.member.dto.response.PostMemberProfileResponse;
import com.example.devcrew.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "기업 회원 상세 정보 입력")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup-company")
    public void registerDetailCompanyMember(
            @RequestBody UpdateCompanyMemberSignUpRequest request) {
        memberService.registerDetailCompanyMember(request);
    }

    @Operation(summary = "자기 소개 작성",description = "highschoolStatus=ENROLLMENT(재학),GRADUATION(졸업) & collegeStatus=ENROLLMENT(재학),ON_LEAVE(휴학),GRADUATION(졸업)")
    @PostMapping("/v1/profile")
    public ResponseEntity<PostMemberProfileResponse> postMemberProfile(@RequestBody @Valid PostMemberProfileRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.postMemberProfile(request));
    }

    @Operation(summary = "자기 소개 조회")
    @GetMapping("/v1/profile")
    public ResponseEntity<GetMemberProfileResponse> getMemberProfile(){
        return ResponseEntity.ok(memberService.getMemberProfile());
    }

    @Operation(summary = "회원 이름 조회")
    @GetMapping("/name")
    public ResponseEntity<GetMemberNameResponse> getMemberName() {
        return ResponseEntity.ok(memberService.getMemberName());
    }

    @Operation(summary = "회원 권한 조회")
    @GetMapping("/role")
    public ResponseEntity<GetMemberRoleResponse> getMemberRole() {
        return ResponseEntity.ok(memberService.getMemberRole());
    }

}
