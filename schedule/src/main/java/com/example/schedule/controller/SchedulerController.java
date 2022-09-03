package com.example.schedule.controller;

import com.example.schedule.config.SchedulerConfiguration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SchedulerController {

  private static final String AUTO_ATTENDANCE_TASK = "autoAttendanceTasks";

  private final ScheduledAnnotationBeanPostProcessor postProcessor;
  private final SchedulerConfiguration schedulerConfiguration;
  private TaskScheduler taskScheduler;

  @PostConstruct
  public void stopInit() {
    postProcessor.postProcessBeforeDestruction(schedulerConfiguration, AUTO_ATTENDANCE_TASK);
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }

  @GetMapping("/start")
  public String startSchedule() {
    postProcessor.postProcessAfterInitialization(schedulerConfiguration, AUTO_ATTENDANCE_TASK);
    return "start";
  }

  @GetMapping("/stop")
  public String stopSchedule() {
    postProcessor.postProcessBeforeDestruction(schedulerConfiguration, AUTO_ATTENDANCE_TASK);
    return "stop";
  }

  @GetMapping("/list")
  public String listSchedule() {
    Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
    if (!setTasks.isEmpty()) {
      return setTasks.toString();
    } else {
      return "Currently no scheduler tasks are running";
    }
  }

  @GetMapping("/start-task")
  public String startTask() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        log.info("run={}", LocalDateTime.now());
        log.info("Only Once Run!");
      }
    };
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    taskScheduler = new ConcurrentTaskScheduler(scheduledExecutorService);
    LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(3);
    Date date = Date.from(localDateTime.atZone(
        ZoneId.systemDefault()).toInstant());
    taskScheduler.schedule(runnable, date);
    log.info("start={}", LocalDateTime.now());
    return "start-task";
  }

}
