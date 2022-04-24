package example.grpcclient;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import service.*;

public class TimerImpl extends TimerGrpc.TimerImplBase{
    public LinkedList<ClockObject> timers = new LinkedList<ClockObject>();
    int seconds = 0;

    @Override
    public void start(TimerRequest req, StreamObserver<TimerResponse> responseObserver) {
        System.out.println("Received from client: " + req.getName());
        TimerResponse.Builder response = TimerResponse.newBuilder();
        ClockObject newEntry = new ClockObject(req.getName());
        boolean flag = false;
        ClockObject barf = new ClockObject("shaun");
        timers.add(barf);
        
        for(ClockObject z : timers) {
            if(z.getName().equals(req.getName())) {
                response.setIsSuccess(false);
                response.setError("Name is already in use");
                break;
            }else if(!z.getName().equals(req.getName())){
                flag = true;
            }
        }
        //here because if we modify in for loop, modifcation exception occurs
        if(flag == true) {
            response.setIsSuccess(true);
            newEntry = new ClockObject(req.getName());
            newEntry.start();
            timers.add(newEntry);
            System.out.println("added entry because did not exist yet");
        }
        flag = false;
        TimerResponse resp = response.build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    
    }
    
    @Override
    public void check(TimerRequest req, StreamObserver<TimerResponse> responseObserver) {
        System.out.println("Received from client: " + req.getName());
        TimerResponse.Builder response = TimerResponse.newBuilder();
        ClockObject newEntry = new ClockObject(req.getName());
        
        Time.Builder timeResponse = Time.newBuilder();
        
        for(ClockObject z : timers) {
            if(z.getName().equals(req.getName())) {
                timeResponse.setName(z.getName());
                timeResponse.setSecondsPassed(z.secondsPassed);
                
                response.setIsSuccess(true);
                response.setTimer(timeResponse);
                
                
                System.out.println("found a user");
                break;
            }
        }
        TimerResponse resp = response.build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
    
    @Override
    public void close(TimerRequest req, StreamObserver<TimerResponse> responseObserver) {
        System.out.println("Received from client: " + req.getName());
        TimerResponse.Builder response = TimerResponse.newBuilder();
        ClockObject newEntry = new ClockObject(req.getName());
        
        Time.Builder timeResponse = Time.newBuilder();
        
        LinkedList<ClockObject> temp = new LinkedList<ClockObject>();
        
        for(ClockObject z : timers) {
            if(z.getName().equals(req.getName())) {
                
                response.setTimer(timeResponse);
                
                System.out.println("found a user: timer has been canceled");
            }else {
                temp.add(z);
            }
            
        }
        //sets list of timers to the list that has all of the still running timers
        timers = temp;
        
        
        
        TimerResponse resp = response.build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
    
    @Override
    public void list(Empty req, StreamObserver<TimerList> responseObserver) {
        
        TimerResponse.Builder response = TimerResponse.newBuilder();
        Time.Builder timeResponse = Time.newBuilder();
        TimerList.Builder list = TimerList.newBuilder();
        
        for(ClockObject z : timers) {
                timeResponse.setName(z.getName());
                timeResponse.setSecondsPassed(z.secondsPassed);
                
                list.addTimers(timeResponse);
                //response.setTimer(list);
                
                System.out.println("found a user: timer has been canceled");
            
            
        }
        list.build();
        //response.setTimer(list);
        TimerList resp = list.build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
    
    
    
    //============================================================
    //INNER CLASS FOR TIMERS AND STUFF
    public class ClockObject{
        String name;
        Timer timer;
        double secondsPassed = 0;
        
        TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                secondsPassed++;
                //System.out.println("Seconds passed: " + secondsPassed);
            }
        };
        
        public void start() {
            timer.scheduleAtFixedRate(task, 1000, 1000);
            
        }
        
        ClockObject(String name) {
            this.name = name;
            this.timer = new Timer();
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public void setTimer(Timer timer) {
            this.timer = timer;
        }
        
        public Timer getTimer() {
            return this.timer;
        }
        
    }
    
    
    
    
}


