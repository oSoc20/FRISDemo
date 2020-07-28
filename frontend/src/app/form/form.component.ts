import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
  // tslint:disable-next-line: max-line-length
  someObject: any = { "id": "2fc28660-7f7c-49f1-aaa4-5e2985deefe4", "englishKeywords": ["Electrocatalysis", "Sustainability"], "dutchKeywords": [], "dataProvider": { "id": "55273531", "name": "KULeuven" }, "title": { "englishTitle": "Electrocatalytic N-functionalisation of olefins towards aziridines and amines", "dutchTitle": "Elektrokatalytische N-functionalisatie van olefines ter vorming van aziridines en amines" }, "empty": false, "abstract": { "id": 296204548, "dutchAbstract": "&lt;p&gt;Heel wat N-bevattende chemicaliÃ«n (aryl- en alkylamines, aziridines)&lt;/p&gt;&lt;p&gt;worden door indirecte methodes gemaakt, wat een slechte&lt;/p&gt;&lt;p&gt;atoomeconomie en veel afvalproductie meebrengt. In de context van&lt;/p&gt;&lt;p&gt;duurzaamheid en gebruik van hernieuwbare energie zijn nieuwe&lt;/p&gt;&lt;p&gt;synthesewegen nodig, vooral voor energierijke molecules zoals&lt;/p&gt;&lt;p&gt;aziridines. Het project combineert elektrokatalyse met eenvoudige&lt;/p&gt;&lt;p&gt;stikstofbronnen, zoals ammoniak, om een aantal N-bevattende&lt;/p&gt;&lt;p&gt;sleutelchemicaliÃ«n te maken.&lt;/p&gt;&lt;p&gt;We starten met de â€˜electrificatieâ€™ van de jodide-gemedieerde&lt;/p&gt;&lt;p&gt;aziridinatie van styreen met ammoniak. Voor deze reactie gebruikten&lt;/p&gt;&lt;p&gt;we eerder een chemische oxidatie, maar nu laten we de reactie&lt;/p&gt;&lt;p&gt;opgaan door een 2-elektron anodische oxidatie. WeÂ ontwikkelen het&lt;/p&gt;&lt;p&gt;proces zowel voor aromatische als alifatische olefines, startend met&lt;/p&gt;&lt;p&gt;commerciÃ«le elektrodes. Vervolgens werken we opÂ Nfunctionalisaties&lt;/p&gt;&lt;p&gt;van olefines via 1-elektron oxidaties. Dit vraagt dat&lt;/p&gt;&lt;p&gt;we de reactiviteit van de N-bron in bedwang houden, en dat we&lt;/p&gt;&lt;p&gt;nieuwe elektrodes ontwerpen voor gecontroleerde oxidatie van de Nbron.&lt;/p&gt;&lt;p&gt;We werken vooral met Ni-bevattende elektrodes, zoals ze ook&lt;/p&gt;&lt;p&gt;voor ureum oxidatie gebruikt worden. Tenslotte tonenÂ we de&lt;/p&gt;&lt;p&gt;opschaling en opwerking van de reacties voor 2 industrieel&lt;/p&gt;&lt;p&gt;interessante molecules.&lt;/p&gt;&lt;p&gt;Â &lt;/p&gt;", "englishAbstract": "&lt;p&gt;Many N-containing key chemicals like aryl- andÂ alkylamines and&lt;/p&gt;&lt;p&gt;aziridines are synthesised through indirect methods, which have very&lt;/p&gt;&lt;p&gt;poor atom efficiency and produce a lot of side products. In the&lt;/p&gt;&lt;p&gt;context of sustainability and using renewable energy sources, new&lt;/p&gt;&lt;p&gt;synthetic approaches are needed, especially for chemicals with high&lt;/p&gt;&lt;p&gt;energy content like aziridines. The proposed research will use&lt;/p&gt;&lt;p&gt;electricity drivenÂ catalytic processes together with simple N-sources&lt;/p&gt;&lt;p&gt;like ammonia to produce N-containing key chemicals.&lt;/p&gt;&lt;p&gt;As a start, the iodide-mediated aziridination with NH3 and styrene as&lt;/p&gt;&lt;p&gt;the model olefin will be â€˜electrifiedâ€™. As developed by our group, this&lt;/p&gt;&lt;p&gt;process used a chemical oxidant, but we will now drive it by a twoelectron&lt;/p&gt;&lt;p&gt;anodic oxidation. We will develop theÂ process both for&lt;/p&gt;&lt;p&gt;aromatic and for aliphaticÂ olefins, using commercially available&lt;/p&gt;&lt;p&gt;electrodes to start with. Next, we will also develop Nfunctionalisations&lt;/p&gt;&lt;p&gt;of olefins that proceed via one-electron oxidations.&lt;/p&gt;&lt;p&gt;This will requireÂ that we harness the reactivity of the N-source, and&lt;/p&gt;&lt;p&gt;that we design new electrode materials for controlled oxidation of the&lt;/p&gt;&lt;p&gt;N-source. We focus on Ni-containing electrodes, well known from&lt;/p&gt;&lt;p&gt;e.g. urea oxidation. Eventually upscaling and workup of the reactions&lt;/p&gt;&lt;p&gt;will be demonstrated for 2 molecules of industrial interest.&lt;/p&gt;" } }
  obj: JSON;
  constructor() { }

  ngOnInit() {
    this.obj = <JSON>this.someObject;
   }

  getKeywords() {
  }

}
