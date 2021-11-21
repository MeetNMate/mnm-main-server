package com.project.mnm.service;

import com.project.mnm.domain.Profile;
import com.project.mnm.domain.User;
import com.project.mnm.dto.ProfileInsertDto;
import com.project.mnm.repository.ProfileRepository;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public Profile getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        return profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 등록되어있지 않습니다."));
    }

    public Profile addProfile(String email, ProfileInsertDto profileInsertDto) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        if (!profileRepository.findByUser(user).isEmpty())
            throw new Exception("이미 프로필이 등록되어 있습니다.");

        String imagePath = null;

        if (profileInsertDto.getImageFile() == null) {
            imagePath = "images/profile/default.png";
        }
        else {
            imagePath = imageService.saveProfileImage(user.getId(), profileInsertDto.getImageFile());
        }

        System.out.println(imagePath);

        return profileRepository.save(Profile.builder()
                .user(user)
                .image(imagePath)
                .name(profileInsertDto.getName())
                .sex(profileInsertDto.getSex())
                .age(profileInsertDto.getAge())
                .description("저와 함께 살 멋쟁이를 구합니다.")
                .build());
    }

    public Profile updateProfile(String email, MultipartFile imageFile, String name, String sex, Integer age, Integer score, String description) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 등록되어있지 않습니다."));

        String imagePath = imageService.saveProfileImage(user.getId(), imageFile);

        profile.setImage(imagePath);
        profile.setName(name);
        profile.setSex(sex);
        profile.setAge(age);
        profile.setScore(score);
        profile.setDescription(description);

        return profileRepository.save(profile);
    }

    public Profile editProfile(String email, MultipartFile imageFile, String name, String sex, Integer age, Integer score) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 등록되어있지 않습니다."));

        if (imageFile != null) {
            String imagePath = imageService.saveProfileImage(user.getId(), imageFile);
            profile.setImage(imagePath);
        }
        if (name != null)
            profile.setName(name);
        if (sex != null)
            profile.setSex(sex);
        if (age != null)
            profile.setAge(age);
        if (score != null)
            profile.setScore(score);

        return profileRepository.save(profile);
    }

    public void deleteProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 등록되어있지 않습니다."));

        // 데이터베이스에서 프로필 삭제 -> 로컬에 저장 된 이미지 파일 삭제 또한 추가해야 함
        profileRepository.delete(profile);
    }
}
