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
  constructor(private gameService:GameService, http:HttpClient,private route:ActivatedRoute) { }
  
  ngOnInit(): void {
    this.load()
    
  }

  load(){
    console.log("playename:"+this.route.snapshot.params["username"])
    this.gameService.getPlayer(this.route.snapshot.params["username"]).subscribe(
      (res:Object)=>{
        this.player=res;
        
        console.log("test hand:"+this.player["hand"].length)
        console.log(this.player)
        if(this.player["hand"].length>0){
          this.condition=true;
        }
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }

 

}
