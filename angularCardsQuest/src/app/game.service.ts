import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NavComponent } from './nav/nav.component';
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class GameService {
  player=''
  private apiServerUrl = environment.apiBaseUrl;
  private imglist =[];  
  constructor(private http:HttpClient) { }

  load(){
    console.log(this.player+" test service");
  }

  private _refreshNeededs= new Subject<void>();

  get refresNeededs () {
    return this._refreshNeededs;
  }

  public getApiUrl(){
    return this.apiServerUrl;
  }
  

//////////////// POST ZONE ////////////////
  /**
  * request to register the player
  * @param name : player name
  * @returns observable which contains empty string when players is not registered and player's name otherwise
  **/
  public  joinGame(name:string): Observable<Object>{
    return this.http.post(this.apiServerUrl+"/joinGame",name);
  }



//////////////// GET ZONE ////////////////
  /**
   * requesting to start the game
   * @param name : player name
   * @returns observable that contains boolean, if player can start the game or not
   */
  public startGame(name:string): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/startGame/"+name);
  }

  /**
   * requesting a player with the specifued name
   * @param name : player name
   * @returns player object if player with given name and null otherwise
   */
   public getPlayer(name:string): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/getPlayer/"+name).pipe(
      tap(()=>{
        this._refreshNeededs.next();
        }
      )
    );  
  }

  /**
   * requesting for updates
   * @param name : player name
   * @returns observable of event queue
   */
   public getUpdates(name:string){
    return this.http.get(this.apiServerUrl+"/getUpdates/"+name)
      .pipe(
        tap(()=>{
          this._refreshNeededs.next();
          }
        )
      );
  }



  /**
   * requesting for CurrentPlayer
   * @returns object of current player
   */
  public getCurrentPlayer(): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/getCurrentPlayer");
  }

 

  /**
   * declining sponsorship
   * @returns observable
   */
  public declineSponsor(){
    return this.http.get(this.apiServerUrl+"/declineSponsorship");
  }

  /**
   * accepting sponsorship
   * @returns observable
   */
   public acceptSponsor(){
    return this.http.get(this.apiServerUrl+"/acceptSponsorship");
  }

/**
 * 
 * @param name player who's accepting patricipation
 * @returns observable of void
 */
  public acceptParticipation(name: string){
    return this.http.get(this.apiServerUrl+"/acceptParticipation/"+name);
  }

  /**
   * 
   * @param name player who's accepting patricipation
   * @returns observable of void
  */
  public rejectParticipation(name: string){
    return this.http.get(this.apiServerUrl+"/rejectParticipation/"+name);
  }

  /**
   * geting active quest
   * @returns obsavable BlogQues
   */
  public getActiveQuest(){
    return this.http.get(this.apiServerUrl+"/getActiveQuest");
  }

  /**
   * get story card
   * @returns obsarvable of card
   */
  public getStoryCard(){
    return this.http.get(this.apiServerUrl+"/getStoryCard");
  }

  /**
   * play ally
   * @param name of payer who's playing ally
   * @param card ally being played
   * @returns observablle of void
   */
  public playAlly(name: string, card: object){
    return this.http.put(this.apiServerUrl+"/playAlly/"+name,card);
  }
  /**
   * get all players status
   * @returns observable of players status
   */
  public getAllPlayersStatus(){
    return this.http.get(this.apiServerUrl+"/getAllPlayersStatus");
  }

  /**
   * sending curent stage
   * @param cards played on curent stage
   * @returns observable of void
   */
  public sendStage(cards: object){
    return this.http.put(this.apiServerUrl+"/receiveStage",cards);
  }

  /**
   * geting stages information
   * @returns observable of stages information
   */

  public getStagePreparationString(){
    return this.http.get(this.apiServerUrl+"/getStagePreparationString");
  }

  public rejectStageSetup(){
    return this.http.get(this.apiServerUrl+"/rejectStageSetup");
  }

  /**
   * getting event log 
   * @returns observable of array list
   */
  public getEventLog(){
    return this.http.get(this.apiServerUrl+"/getEventLog");
  }

  /**
   * discarding card
   * @param name of player who's discarding the card
   * @param card to be discarded
   * @returns 
   */
  public discardCard(name:string, card:object){
    return this.http.put(this.apiServerUrl+"/discardCard/"+name,card);
  }

  /**
   * getting winner
   * @returns winners name
   */
  public getWinner(){
    return this.http.get(this.apiServerUrl+"/getWinner");
  }

  /**
   * get stage playing string
   * @returns observable of Stage Playing String
   */
  public getStagePlayingString(){
    return this.http.get(this.apiServerUrl+"/getStagePlayingString");
  }

  /**
   * get event
   * @returns observable of event string
   */
  public getEvent(){
    return this.http.get(this.apiServerUrl+"/getEvent");
  }

  public acceptEvent(name: string){
    return this.http.put(this.apiServerUrl+"/acceptEvent",name);
  }

//////////////// PUT ZONE ////////////////

  /**
   * send playable hand to quest foe
   * @returns obsarvable of void
   */
  public sendPlayableHand(name :string, hand: Array<object>){
    return this.http.put(this.apiServerUrl+"/questFoeReceivePlayableHand/"+name, hand);
  }
  
  
  public sendingStages(stages: Array<Array<object>>){
    return this.http.put(this.apiServerUrl+"/receiveStages",stages);
  }



//////////////// TEST ZONE ////////////////
  public getImages(){ //<--- WILL BE IN GET ZONE
    //return this.http.get(this.apiServerUrl+"/getImg");
    return this.imglist;
  }
  
  /**
   * requesting to send cards and goes to next player's turn !!!==> It is designed for preview
   * @param card : card list
   * @returns observable that contains boolean, for whether send is sent succesfully
   */
  public sendSelected(card: string[]){ //<--- WILL BE IN POST ZONE
    return this.http.post(this.apiServerUrl+"/submitted", card);

  }
  public sendHanded(card: string[]){
    return this.http.post(this.apiServerUrl+"/handed", card); 
  }
}
