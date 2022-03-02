import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  player='';
  constructor(private gameService:GameService) { }

  ngOnInit(): void {
    
  }

  join(){
    console.log(this.player+" has joined")
    this.player=''    
  }

}
