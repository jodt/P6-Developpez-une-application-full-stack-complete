import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { forkJoin } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormValidationErrorService {
  constructor() {}

  public getErrorMessage(form: FormGroup, field: string): string | null {
    if (form.get(field)?.hasError('required')) {
      return 'Champ obligatoire';
    } else if (form.get(field)?.hasError('email')) {
      return "Format d'email invalide";
    } else if (form.get(field)?.hasError('passwordStrength')) {
      return 'Le mot de passe doit comporter une majuscule, une minuscule, un chiffre, un caractere special et au moins 8 caractères';
    } else if (form.get(field)?.hasError('userNameTaken')) {
      return "Ce nom d'utilisateur appartient déjà à un compte";
    } else if (form.get(field)?.hasError('emailTaken')) {
      return 'Cet email appartient déjà à un compte';
    } else if (form.get(field)?.hasError('minlength')) {
      let requiredLength = form.get(field)?.errors?.['minlength']?.requiredLength;
      return `Ce champs doit avoir ${requiredLength} caractères minimum`;
    } else {
      return null;
    }
  }
}
