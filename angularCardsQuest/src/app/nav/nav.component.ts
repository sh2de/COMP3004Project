import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  player='';
  constructor(private gameService:GameService) { }
  
  
  ngOnInit(): void {
    this.load();
  }

  load(){
    
  }

}
