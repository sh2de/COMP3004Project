import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: ['./gameboard.component.scss']
})
export class GameboardComponent implements OnInit {

  constructor(private gameService:GameService) { }
  player='';
  ngOnInit(): void {
    
  }

  load(){

  }

}
