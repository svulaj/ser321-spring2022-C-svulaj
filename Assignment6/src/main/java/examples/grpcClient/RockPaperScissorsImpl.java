package example.grpcclient;

import java.util.LinkedList;
import java.util.Random;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerMethodDefinition;
import io.grpc.stub.StreamObserver;
import service.*;

public class RockPaperScissorsImpl extends RockPaperScissorsGrpc.RockPaperScissorsImplBase{

    LinkedList<LeaderboardEntry> leaderBoard = new LinkedList<LeaderboardEntry>();
    @Override
    public void play(PlayReq req, StreamObserver<PlayRes> responseObserver) {
        PlayRes.Builder response = PlayRes.newBuilder();
        Random random = new Random();
        
        if(req.getPlayValue() == 0) {
            System.out.println("Received from client: " + req.getName());
            System.out.println("Received from client: " + req.getPlayValue());
            System.out.println("Received from client: " + "ROCK");
            response.setIsSuccess(true);
        }else if(req.getPlayValue() == 1) {
            System.out.println("Received from client: " + req.getName());
            System.out.println("Received from client: " + req.getPlayValue());
            System.out.println("Received from client: " + "PAPER");
            response.setIsSuccess(true);
        }else if(req.getPlayValue() == 2) {
            System.out.println("Received from client: " + req.getName());
            System.out.println("Received from client: " + req.getPlayValue());
            System.out.println("Received from client: " + "SCISSORS");
            response.setIsSuccess(true);
        }
        
        // 0 = rock, 1 = paper, 2 == scissors
//        if(random.nextInt(2) == 0 && req.getPlayValue() == 1) {
//            System.out.println("player wins: paper beats rock");
//        }else if(random.nextInt(2) == 1 && req.getPlayValue() == 2) {
//            System.out.println("player wins: scissors beats paper");
//        }else if(random.nextInt(2) == 2 && req.getPlayValue() == 0) {
//            System.out.println("player wins: rock beats scissors");
//        }
        int computerChoice;
        if(req.getPlayValue() == 0) {
            computerChoice = random.nextInt(3);
            if(computerChoice == 0) {
                System.out.println("Computer played: ROCK");
                response.setMessage("Player played: ROCK. BUT so did computer.... DRAW");
            }else if(computerChoice == 1) {
                System.out.println("computer played: PAPER");
                response.setMessage("Player played: ROCK. BUT computer player: PAPER.... YOU LOSE");
            }else if(computerChoice == 2) {
                System.out.println("computer played: SCISSORS");
                response.setMessage("Player played: ROCK. BUT computer player: SCISSORS.... YOU WIN");
            }
        }
        
        
        
        
        PlayRes resp = response.build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
        
    }
}
