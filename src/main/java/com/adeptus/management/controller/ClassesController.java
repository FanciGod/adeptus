package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.CreateNewClassRequest;
import com.adeptus.management.dto.request.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassesResponse;
import com.adeptus.management.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("class")
@RequiredArgsConstructor
public class ClassesController {
    private final ClassesService classesService;

    @GetMapping("all")
    ApiResponse<Page<ClassesResponse>> getAllClassesWithPagination(@RequestParam int page, @RequestParam int size){
        return ApiResponse.<Page<ClassesResponse>>builder()
                .result(classesService.findAllClassesWithPagination(page,size))
                .build();
    }

    @GetMapping("info/{id}")
    ApiResponse<ClassesResponse> getClassById(@PathVariable Long id){
        return ApiResponse.<ClassesResponse>builder()
                .result(classesService.findClassById(id))
                .build();
    }

    @PostMapping("new")
    ApiResponse<ClassesResponse> createNewClass(@RequestBody CreateNewClassRequest request){
        return ApiResponse.<ClassesResponse>builder()
                .result(classesService.createNewClass(request))
                .build();
    }

    @PutMapping("update/{id}")
    ApiResponse<ClassesResponse> updateClassById(@PathVariable Long id,@RequestBody UpdateClassRequest request){
        return ApiResponse.<ClassesResponse>builder()
                .result(classesService.updateClassById(id,request))
                .build();
    }

    @DeleteMapping("delete/{id}")
    ApiResponse<?> deleteClassById(@PathVariable Long id){
        classesService.deleteClassById(id);
        return ApiResponse.builder().build();
    }
}
