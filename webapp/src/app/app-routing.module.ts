import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: '/webportalsign-in', pathMatch: 'full'},
  {path: '', loadChildren: () => import('./shared/shared.module').then(m => m.Shared)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
