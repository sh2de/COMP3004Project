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
  private player:object;
  constructor(private http:HttpClient) { }

  load(){
    
  }

  public getHello(): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/hello");
  }

  public joinGame(name:string){
    this.http.post(this.apiServerUrl+"/join/",name).subscribe(
      (res:Object)=>{
        this.player=res;
        console.log(this.player);
      },
      (err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    )
  }
}
