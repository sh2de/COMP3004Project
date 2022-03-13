import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: ['./gameboard.component.scss']
})
export class GameboardComponent implements OnInit {
  player= new Object;
  constructor(private gameService:GameService) { }
  
  ngOnInit(): void {
    this.load()
  }

  load(){
    this.player=this.gameService.getPlayer()
    console.log("test player:"+this.gameService.getPlayer())
  }

}
