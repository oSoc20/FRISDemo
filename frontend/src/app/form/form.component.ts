import { Component, OnInit } from '@angular/core';
import { ResearchService } from '../services/research.service';
import { Research } from '../models/research.model';
import { Project } from '../models/project.model';
import { Publication } from '../models/publication.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
  list = [];
  selected;
  data: Research;
  dataToShow;
  keywordResponse;
  constructor(private researchService: ResearchService) { }

  ngOnInit() {

    this.researchService.getResearchFromFile().subscribe(result => {
      console.log(result);
      this.data = result;

      for (let obj of result.projects) {
        if (obj.titleEn != null) {
          this.list.push({ id: obj.uuid, title: obj.titleEn, type: 'project' });
        }

      }
      for (const obj of result.publications) {
        if (obj.titleEn != null) {
          this.list.push({ id: obj.uuid, title: obj.titleEn, type: 'publication' });
        }
      }
      console.log(this.list);
    });

    // this.researchService.getResearch().then(result => {
    //   console.log(result);
    //   this.data = result;

    //   for (let obj of result.projects) {
    //     if (obj.titleEn != null) {
    //       this.list.push({ id: obj.uuid, title: obj.titleEn, type: 'project' });
    //     }

    //   }
    //   for (const obj of result.publications) {
    //     if (obj.titleEn != null) {
    //       this.list.push({ id: obj.uuid, title: obj.titleEn, type: 'publication' });
    //     }
    //   }
    //   console.log(this.list);
    // });
  }

  showData($event) {
    console.log(this.selected);

    let obj = this.list.filter(x => x.id === this.selected);
    console.log(obj);
    let dataSelected;
    console.log(obj[0]['type'])
    if (obj[0]['type'] === 'project') {

      dataSelected = this.data.projects.filter(x => x.uuid === this.selected);
      this.dataToShow = new Project(dataSelected[0].uuid, dataSelected[0].titleEn, dataSelected[0].titleNl, dataSelected[0].keywordsEn, dataSelected[0].keywordsNl, dataSelected[0].abstractEn, dataSelected[0].abstractNl);

    } else if (obj[0]['type'] === 'publication') {
      dataSelected = this.data.publications.filter(x => x.uuid === this.selected);
      this.dataToShow = new Publication(dataSelected[0].uuid, dataSelected[0].titleEn, dataSelected[0].titleNl, dataSelected[0].keywordsEn, dataSelected[0].keywordsNl, dataSelected[0].abstractEn, dataSelected[0].abstractNl, dataSelected[0].doi);
    }
    if (this.dataToShow.abstractEn == null) {
      this.dataToShow.abstractEn = "";
    }
    if (this.dataToShow.abstractNl == null) {
      this.dataToShow.abstractEn = "";
    }

    if (this.dataToShow.titleEn == null) {
      this.dataToShow.titleEn = "";
    }
    if (this.dataToShow.titleNl == null) {
      this.dataToShow.titleNl = "";
    }
    console.log(this.dataToShow)
  }

  enrichData() {
    console.log(this.dataToShow);
    console.log(this.dataToShow instanceof Project);
    if (this.dataToShow instanceof Project) {
      this.researchService.enrichProject(this.dataToShow).subscribe(result => {
        console.log(result);
        this.keywordResponse = result;
      });
    } else if (this.dataToShow instanceof Publication) {
      this.researchService.enrichPublication(this.dataToShow).subscribe(result => {
        console.log(result);
        this.keywordResponse = result;
      });
    }
  }
}
