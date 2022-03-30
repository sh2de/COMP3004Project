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
  cardList=[];
  myHand=[];
  selectedCards=[];
  roundNUM=0;
  ready: Boolean;
  sponsorReq: Boolean;
  prevUpdatesLen: number;
  areSelected:Boolean;

  stages=[];

  constructor(private gameService:GameService, http:HttpClient,private route:ActivatedRoute) { }
  
  ngOnInit(): void {
    this.prevUpdatesLen=0;
    this.ready=true;
    this.sponsorReq=false;
    this.areSelected=false;
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

    this.cardList = this.gameService.getImages();
  }

  /**
   * command to the next steps
   */
  nextStage(){
    
    this.stages.push(this.selectedCards);
    console.log(this.stages)
    this.selectedCards=[];    
    this.areSelected=false;
    
  }
  /**
   * ready to start the game
   */
  readyToStart(){
    this.ready=false;
    this.gameService.startGame(this.playerName).subscribe(
      (res)=>{
        console.log(res)
        
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }
  start(){
    this.roundNUM += 1;
    if(this.stages['length']==3){
      this.gameService.sendingStages(this.stages).subscribe(
        (res)=>{},
        (err:HttpErrorResponse)=>{
          console.log(err.message);
        }
      )
    }
    this.stages=[];
  }
  send(){
    this.gameService.sendSelected(this.selectedCards);
    this.gameService.sendHanded(this.myHand);
  }

  /**
   * get players updates
   */
  getUpdates(){
    this.gameService.getUpdates(this.playerName).subscribe(
      (res:Object)=>{
        
        // console.log(res)
        if(res["length"]>this.prevUpdatesLen){
          this.prevUpdatesLen=res["length"]
          console.log("test updates: ");
          console.log(res)
          //test updates 
          if(res[this.prevUpdatesLen-1]=="ALL_PLAYERS_READY"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.load()
          }

          if(res[this.prevUpdatesLen-1]=="REQUEST_SPONSORSHIP"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.sponsorReq=true;
          }

          if(res[this.prevUpdatesLen-1]=="DRAW_STORY"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="CREATE_QUEST"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="WAIT_FOR_QUEST_CREATION"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_FOE_SELECT_CARDS"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_FOE_SHOW_RESULTS"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_OVER"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_START"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

        }
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
  }

  acceptSponsor(){
    console.log("i can sponsor it");
    this.gameService.acceptSponsor().subscribe(
      (res)=>{},
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }

    )
  }

  declineSponsor(){
    console.log("not sponsoring it")
    this.gameService.acceptSponsor().subscribe(
      (res)=>{},
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    );
  }

  selected(card){
  
    this.selectedCards.push(card);
    console.log(card)
  }

  addToplayerList(i:number){
    this.myHand.push(this.cardList[i]);
  }

  isSelected(i:number){  

    this.selectedCards.push(this.myHand[i]);
    this.myHand.splice(i, 1);
    this.areSelected=true;
    console.log(this.myHand[i]);
  }
  cancel(i:number){
    this.myHand.push(this.selectedCards[i]);
    this.selectedCards.splice(i, 1);

  }
 

}
