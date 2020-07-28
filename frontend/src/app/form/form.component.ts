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
  constructor(private researchService: ResearchService) { }

  ngOnInit() {

    this.researchService.getResearch().then(result => {
      console.log(result);

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
    // this.researchService.getResearch().subscribe(result => {
    //   this.data = result;

    //   for (let obj of result.projects) {
    //     if (obj.title.englishTitle != null) {
    //       this.list.push({ id: obj.id, title: obj.title.englishTitle, type: 'project' });
    //     }

    //   }
    //   for (const obj of result.publications) {
    //     if (obj.titleEn != null) {
    //       this.list.push({ id: obj.uuid, title: obj.titleEn, type: 'publication' });
    //     }
    //   }
    //   console.log(this.list);
    // });

    // this.researchService.getResearch().subscribe(result => {
    //     console.log(result);
    //     let projects: Project[];
    //     let publications: Publication[];
    //     result.projects.forEach(p => {
    //       let project = new Project(p.id, p.title.englishTitle, p.title.dutchTitle, p.englishKeywords, p.dutchKeywords,p.abstract.englishAbstract, p.abstract.dutchAbstract);
    //       projects.push(project);
    //     });
    //     result.publications.forEach(p => {
    //       let publication = new Publication(p.uuid, p.titleEn, p.titleNl, p.keywordsEn, p.keywordsNl, p.abstractEn, p.abstractNl, p.doi);
    //       publications.push(publication);
    //     });
    //     let research = new Research(projects, publications);
    //     this.data = research;
    //   });
  }

  showData($event) {
    console.log(this.selected);

    let obj = this.list.filter(x => x.id === this.selected);
    console.log(obj);
    let dataSelected;
    if (obj['type'] === 'project') {
      dataSelected = this.data.projects.filter(x => x.uuid === this.selected);
    } else {
      dataSelected = this.data.projects.filter(x => x.uuid === this.selected);
    }
    this.dataToShow = dataSelected[0];
  }

  enrichData() {
    if (true) {
      this.researchService.enrichProject(this.dataToShow);
    } else {
      this.researchService.enrichProject(this.dataToShow);
    }

  }
}
