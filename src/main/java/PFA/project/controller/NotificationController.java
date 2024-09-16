//package PFA.project.controller;
//
//import PFA.project.dto.notificationDto.NotificationDto;
//import PFA.project.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/notification")
//public class NotificationController {
//    @Autowired
//    private NotificationService notificationService;
//
//    @GetMapping("getAllNotificationsByReceiver")
//    public ResponseEntity<List<NotificationDto>> getAllNotificationsByReceiver(@RequestParam("uuidReceiver") UUID uuidReceiver) {
//        return ResponseEntity.ok().body(notificationService.getAllValidNotificationsByReceiver(uuidReceiver));
//    }
//
//    @PostMapping ("seenNotificationsByReceiver")
//    public ResponseEntity<Void> seenNotifications(@RequestParam("uuidReceiver") UUID uuidReceiver) {
//        notificationService.seenNotifications(uuidReceiver);
//        return ResponseEntity.ok().build();
//
//    }
//}
