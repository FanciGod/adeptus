package com.adeptus.management.exception;

public class StudentOutOfLessonsException extends RuntimeException {
  public StudentOutOfLessonsException(Long studentId) {
    super("Học viên " + studentId + " đã hết số buổi học, cần đóng học phí");
  }
}

