import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NavComponent } from './nav/nav.component';


@Injectable({
  providedIn: 'root'
})
export class GameService {
  player=''
  constructor(private router:Router) { }

  load(){
    console.log(this.player+" test service");
  }
}
