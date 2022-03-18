import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NavComponent } from './nav/nav.component';


@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiServerUrl = environment.apiBaseUrl;

  private player= new Object;
  
  constructor(private http:HttpClient) { }

  load(){
    
  }

  public getHello(): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/hello");
  }
  
  /*
  request to register the player unde inputed name
  return observable which contains empty string when players is not registered and player's name otherwise
  */
  public  joinGame(name:string): Observable<Object>{
    return this.http.post(this.apiServerUrl+"/joinGame",name);
  }
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
    return this.http.get(this.apiServerUrl+"/getPlayer/"+name);
  }

  /**
   * requesting for updates
   * @param name : player name
   * @returns observable of event queue
   */
   public getUpdates(name:string): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/getUpdates/"+name);
  }


}
