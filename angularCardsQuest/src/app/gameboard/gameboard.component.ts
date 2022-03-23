import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../game.service';

@Component({
  selector: 'app-gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: ['./gameboard.component.scss']
})
export class GameboardComponent implements OnInit {
  player= new Object;
  playerName="";
  condition=false;
  myHand=[];
  selectedCards=[];
  constructor(private gameService:GameService, http:HttpClient,private route:ActivatedRoute) { }
  
  ngOnInit(): void {
    this.playerName=this.route.snapshot.params["username"];
    this.gameService.refresNeededs
      .subscribe(()=>{
        this.getUpdates();
      });
      this.load()
  }

  load(){
    console.log("playename:"+this.route.snapshot.params["username"])

    this.gameService.getPlayer(this.playerName).subscribe(
      (res:Object)=>{
        this.player=res;
        this.myHand=this.player["hand"];
        console.log("test hand:"+this.player["hand"].length)       
        
        if(this.player["hand"].length>0){
          this.condition=true;
        }
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }

  start(){
    this.gameService.startGame(this.playerName).subscribe(
      (res)=>{
        if(res["length"]>0){
          console.log(res)
        }
        
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }

  getUpdates(){
    this.gameService.getUpdates(this.playerName).subscribe(
      (res:Object)=>{
        console.log(res)
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }

  selected(card){
  
    this.selectedCards.push(card);
    console.log(card)
  }

 

}
