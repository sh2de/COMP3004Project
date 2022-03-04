import { HttpClient } from '@angular/common/http';
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

  constructor(private http:HttpClient) { }

  load(){
    
  }

  public getHello(): Observable<Object>{
    return this.http.get(this.apiServerUrl+"/hello");
  }
}
