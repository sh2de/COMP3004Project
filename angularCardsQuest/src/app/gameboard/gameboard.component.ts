import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { convertCompilerOptionsFromJson } from 'typescript';
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
  participate:Boolean;
  activeQuest={};
  storyCard={};
  stages=[];
  playableHand=[];
  questCondition:Boolean;
  questCreator:Boolean;
  questPlayer:Boolean;
  foeWarning:Boolean;

  constructor(private gameService:GameService, http:HttpClient,private route:ActivatedRoute) { }
  
  ngOnInit(): void {
    this.prevUpdatesLen=0;
    this.questCondition=false;
    this.ready=true;
    this.participate=false;
    this.sponsorReq=false;
    this.areSelected=false;
    this.questCreator=true;
    this.questPlayer=false;
    this.foeWarning=false;
    this.playerName=this.route.snapshot.params["username"];
    this.gameService.refresNeededs
      .subscribe(()=>{
        this.getUpdates();
      });
    this.load()
    this.getUpdates();
    this.getActiveQuest();

      
  }

  load(){
    console.log("playename:"+this.route.snapshot.params["username"])

    this.gameService.getPlayer(this.playerName).subscribe(
      (res:Object)=>{
        this.player=res;
        this.myHand=this.player["hand"];
        
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
   * accepting participation
   */
  acceptParticipation(){
    this.gameService.acceptParticipation(this.playerName).subscribe(
      (res)=>{},
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    )
    this.participate=false;
    this.questPlayer=true;
    this.questCreator=false;
    this.load()
  }

  /**
   * decline participation
   */
   declineParticipation(){
    this.gameService.rejectParticipation(this.playerName).subscribe(
      (res)=>{},
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    )
    this.participate=false;
  }

  /**
   * command to the next steps
   */
  nextStage(){    
    this.roundNUM += 1;
    this.stages = this.selectedCards;
    console.log(this.stages)
    this.selectedCards=[];    
    this.areSelected=false;
    
  }

  /**
   * get story card
   */
  getStoryCard(){
    this.gameService.getStoryCard().subscribe(
      (res)=>{
        console.log("stor card");
        this.storyCard=res;
        console.log("test url "+res['url']);

        console.log("story card");
        console.log(res);

      },
      (err:HttpErrorResponse)=>{
        console.log("ERRO:"+err.message);
      }
    )
  }

  /**
   * get active quest
   */
   getActiveQuest(){
    this.gameService.getActiveQuest().subscribe(
      (res)=>{
        this.activeQuest=res;
        
        console.log("active quest");
        console.log(res);
      },
      (err:HttpErrorResponse)=>{
        console.log("ERRO:"+err.message);
      }
    )
  }

  /**
   * sending playable hand to quest Foe
   */
   sendPlayableHand(){
     console.log("playable hand")
     console.log(this.playableHand)
     this.gameService.sendPlayableHand(this.playerName,this.playableHand).subscribe(
       (res)=>{},
       (err:HttpErrorResponse)=>{
        console.log("ERRO:"+err.message);
       }
     )
     this.playableHand=[];
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

  /**
   * start the game
   */
  start(){
    this.nextStage();
    console.log(this.stages);
    this.gameService.sendingStages(this.stages).subscribe(
      (res)=>{
        console.log("stages responses")
      },
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    )
    for(let i = 0; i < this.myHand.length; i++){
      if(document.getElementById(`${i}`).style.border=="5px solid rgb(255, 0, 0)"){
        document.getElementById(`${i}`).style.border="0";
      }
    }
    this.selectedCards=[];
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
        if(res["length"]>0){
          this.prevUpdatesLen=res["length"]
          console.log("test updates: ");
          console.log(res)
          //test updates 
          if(res[this.prevUpdatesLen-1]=="ALL_PLAYERS_READY"){
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.load()
          }

          if(res[this.prevUpdatesLen-1]=="REQUEST_SPONSORSHIP"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.sponsorReq=true;
          }

          if(res[this.prevUpdatesLen-1]=="DRAW_STORY"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="CREATE_QUEST"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.questCondition=true;
            this.getActiveQuest();
            this.getStoryCard();
          }

          if(res[this.prevUpdatesLen-1]=="WAIT_FOR_QUEST_CREATION"){
            this.participate=true;

            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
            this.getActiveQuest();
            this.getStoryCard();
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_FOE_SELECT_CARDS"){
            this.load()
            this.foeWarning=true;
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_FOE_SHOW_RESULTS"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }
          
          if(res[this.prevUpdatesLen-1]=="QUEST_OVER"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="QUEST_START"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="UPDATE_PLAYERS"){
            this.load()
            console.log("updates "+res[this.prevUpdatesLen-1]);
          }

          if(res[this.prevUpdatesLen-1]=="DISCARD_NEEDED"){
            this.load()
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
    this.sponsorReq=false;
  }

  declineSponsor(){
    console.log("not sponsoring it")
    this.gameService.acceptSponsor().subscribe(
      (res)=>{},
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    );
    this.sponsorReq=false;
  }

  selected(i:number){
    console.log(this.myHand[i]);
    this.playableHand.push(this.myHand[i]);
    this.myHand.splice(i, 1);
    this.areSelected=true;
    
  }

  addToplayerList(i:number){
    this.myHand.push(this.cardList[i]);
  }

  isSelected(i:number){
    if(document.getElementById(`${i}`).style.border=="0px"){
      document.getElementById(`${i}`).style.border="5px solid rgb(255, 0, 0)";  
      this.selectedCards.push(this.myHand[i]);
    }else{
      document.getElementById(`${i}`).style.border="0";
      this.selectedCards.forEach(
        selectedCards => {
          if(selectedCards == this.myHand[i]){
            this.selectedCards.splice(this.selectedCards.indexOf(selectedCards), 1);
          }
        }
      );
      //this.selectedCards.splice(i, 1);
    }
    console.log(this.myHand[i]);
    //this.myHand.splice(i, 1);
    this.areSelected=true;
    
  }
  cancel(i:number){
    this.myHand.push(this.selectedCards[i]);
    this.selectedCards.splice(i, 1);

  }
 

}
