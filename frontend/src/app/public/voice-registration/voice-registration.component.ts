import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InscriptionOpenService } from "../../shared/service/open/profil/inscriptionOpen.service";
import { MessageService } from 'primeng/api';

interface ChatMessage {
    text: string;
    isBot: boolean;
    timestamp: Date;
}

interface RegistrationField {
    name: string;
    question: {
        fr: string;
        en: string;
        es: string;
        ar: string;
    };
    validator: any;
    value: string;
    options?: string[];
}

@Component({
    selector: 'app-voice-registration',
    templateUrl: './voice-registration.component.html',
    styleUrls: ['./voice-registration.component.scss'],
    providers: [MessageService]
})
export class VoiceRegistrationComponent implements OnInit {
    registrationForm: FormGroup;
    chatMessages: ChatMessage[] = [];
    isListening = false;
    recognitionInstance: any;
    currentLanguage = 'fr';
    availableLanguages = [
        { code: 'fr', name: 'Français' },
        { code: 'en', name: 'English' },
        { code: 'es', name: 'Español' },
        { code: 'ar', name: 'العربية' }
    ];

    registrationFields: RegistrationField[] = [
        {
            name: 'nom',
            question: {
                fr: 'Quel est votre nom de famille ?',
                en: 'What is your last name?',
                es: '¿Cuál es su apellido?',
                ar: 'ما هو اسم عائلتك؟'
            },
            validator: Validators.required,
            value: ''
        },
        {
            name: 'prenom',
            question: {
                fr: 'Quel est votre prénom ?',
                en: 'What is your first name?',
                es: '¿Cuál es su nombre?',
                ar: 'ما هو اسمك الأول؟'
            },
            validator: Validators.required,
            value: ''
        },
        {
            name: 'email',
            question: {
                fr: 'Quelle est votre adresse email ?',
                en: 'What is your email address?',
                es: '¿Cuál es su dirección de correo electrónico?',
                ar: 'ما هو بريدك الإلكتروني؟'
            },
            validator: [Validators.required, Validators.email],
            value: ''
        },
        {
            name: 'password',
            question: {
                fr: 'Veuillez choisir un mot de passe ?',
                en: 'Please choose a password?',
                es: '¿Por favor elija una contraseña?',
                ar: 'الرجاء اختيار كلمة مرور؟'
            },
            validator: [Validators.required, Validators.minLength(8)],
            value: ''
        },
        {
            name: 'langue',
            question: {
                fr: 'Quelle langue préférez-vous ? Dites "français", "anglais" ou "bilingue".',
                en: 'Which language do you prefer? Say "French", "English", or "Bilingual".',
                es: '¿Qué idioma prefiere? Diga "francés", "inglés" o "bilingüe".',
                ar: 'ما هي اللغة التي تفضلها؟ قل "فرنسي" أو "إنجليزي" أو "ثنائي اللغة".'
            },
            validator: Validators.required,
            value: '',
            options: ['français', 'anglais', 'bilingue']
        },
        {
            name: 'niveauLangue',
            question: {
                fr: 'Quel est votre niveau de langue ? Dites "débutant", "intermédiaire" ou "avancé".',
                en: 'What is your language level? Say "beginner", "intermediate", or "advanced".',
                es: '¿Cuál es su nivel de idioma? Diga "principiante", "intermedio" o "avanzado".',
                ar: 'ما هو مستواك اللغوي؟ قل "مبتدئ" أو "متوسط" أو "متقدم".'
            },
            validator: Validators.required,
            value: '',
            options: ['débutant', 'intermédiaire', 'avancé']
        },
        {
            name: 'metier',
            question: {
                fr: 'Quel est votre métier ?',
                en: 'What is your profession?',
                es: '¿Cuál es su profesión?',
                ar: 'ما هي مهنتك؟'
            },
            validator: Validators.required,
            value: ''
        },
        {
            name: 'nombreHeureExperience',
            question: {
                fr: 'Combien d\'heures d\'expérience avez-vous dans ce métier ?',
                en: 'How many hours of experience do you have in this profession?',
                es: '¿Cuántas horas de experiencia tiene en esta profesión?',
                ar: 'كم عدد ساعات الخبرة لديك في هذه المهنة؟'
            },
            validator: [Validators.required, Validators.pattern('^[0-9]+$')],
            value: ''
        },
        {
            name: 'phone',
            question: {
                fr: 'Quel est votre numéro de téléphone ?',
                en: 'What is your phone number?',
                es: '¿Cuál es su número de teléfono?',
                ar: 'ما هو رقم هاتفك؟'
            },
            validator: [Validators.required, Validators.pattern('^[0-9]{10}$')],
            value: ''
        },
        {
            name: 'description',
            question: {
                fr: 'Pouvez-vous décrire brièvement votre expérience et compétences ?',
                en: 'Can you briefly describe your experience and skills?',
                es: '¿Puede describir brevemente su experiencia y habilidades?',
                ar: 'هل يمكنك وصف خبرتك ومهاراتك باختصار؟'
            },
            validator: Validators.required,
            value: ''
        }
    ];

    currentFieldIndex = -1;
    registrationCompleted = false;
    processingRegistration = false;

    constructor(
        private fb: FormBuilder,
        private inscriptionOpenService: InscriptionOpenService,
        private messageService: MessageService
    ) { }

    ngOnInit() {
        this.setupForm();
        this.startConversation();

        if (!('webkitSpeechRecognition' in window) && !('SpeechRecognition' in window)) {
            this.addBotMessage({
                fr: 'Désolé, votre navigateur ne supporte pas la reconnaissance vocale. Veuillez utiliser Chrome, Edge ou Safari récent.',
                en: 'Sorry, your browser does not support speech recognition. Please use Chrome, Edge, or recent Safari.',
                es: 'Lo sentimos, su navegador no admite el reconocimiento de voz. Utilice Chrome, Edge o Safari reciente.',
                ar: 'عذرًا، متصفحك لا يدعم التعرف الصوتي. يرجى استخدام Chrome أو Edge أو Safari الحديث.'
            });
        }
    }

    setupForm() {
        const formGroup = {};
        this.registrationFields.forEach(field => {
            formGroup[field.name] = ['', field.validator];
        });
        this.registrationForm = this.fb.group(formGroup);
    }

    startConversation() {
        this.addBotMessage({
            fr: 'Bonjour! Je suis votre assistant d\'inscription vocale. Vous pouvez changer la langue à tout moment. Pour commencer, cliquez sur le microphone.',
            en: 'Hello! I am your voice registration assistant. You can change the language at any time. To begin, click on the microphone.',
            es: '¡Hola! Soy su asistente de registro por voz. Puede cambiar el idioma en cualquier momento. Para comenzar, haga clic en el micrófono.',
            ar: 'مرحبًا! أنا مساعد التسجيل الصوتي الخاص بك. يمكنك تغيير اللغة في أي وقت. للبدء، انقر على الميكروفون.'
        });

        setTimeout(() => {
            this.nextQuestion();
        }, 1000);
    }

    addBotMessage(messageByLang: {[key: string]: string}) {
        const message = messageByLang[this.currentLanguage] || messageByLang['en'];
        this.chatMessages.push({
            text: message,
            isBot: true,
            timestamp: new Date()
        });

        if ('speechSynthesis' in window) {
            const speech = new SpeechSynthesisUtterance(message);
            speech.lang = this.getSpeechRecognitionLocale();

            if (this.currentLanguage === 'ar') {
                const voices = window.speechSynthesis.getVoices();
                const arabicVoice = voices.find(v => v.lang.includes('ar') || v.lang.includes('arabic'));
                if (arabicVoice) {
                    speech.voice = arabicVoice;
                }
            }

            window.speechSynthesis.speak(speech);
        }
    }

    addUserMessage(message: string) {
        this.chatMessages.push({
            text: message,
            isBot: false,
            timestamp: new Date()
        });
    }

    getSpeechRecognitionLocale() {
        const langMappings = {
            'fr': 'fr-FR',
            'en': 'en-US',
            'es': 'es-ES',
            'ar': 'ar-SA'
        };
        return langMappings[this.currentLanguage] || 'en-US';
    }

    startListening() {
        if (this.isListening) return;

        if ('speechSynthesis' in window) {
            window.speechSynthesis.cancel();
        }

        const SpeechRecognition = window['SpeechRecognition'] || window['webkitSpeechRecognition'];
        this.recognitionInstance = new SpeechRecognition();
        this.recognitionInstance.lang = this.getSpeechRecognitionLocale();
        this.recognitionInstance.continuous = false;
        this.recognitionInstance.interimResults = false;

        this.isListening = true;

        this.recognitionInstance.onresult = (event) => {
            const transcript = event.results[0][0].transcript;
            this.isListening = false;
            this.processUserResponse(transcript);
        };

        this.recognitionInstance.onerror = (event) => {
            console.error('Speech recognition error', event.error);
            this.isListening = false;
            this.addBotMessage({
                fr: 'Désolé, je n\'ai pas pu vous entendre. Veuillez réessayer.',
                en: 'Sorry, I couldn\'t hear you. Please try again.',
                es: 'Lo siento, no pude escucharte. Por favor, inténtalo de nuevo.',
                ar: 'عذرًا، لم أستطع سماعك. يرجى المحاولة مرة أخرى.'
            });
        };

        this.recognitionInstance.onend = () => {
            this.isListening = false;
        };

        this.recognitionInstance.start();
    }

    stopListening() {
        if (this.recognitionInstance) {
            this.recognitionInstance.stop();
            this.isListening = false;
        }
    }

    changeLanguage(langCode: string) {
        this.currentLanguage = langCode;
        this.addBotMessage({
            fr: 'Langue changée en Français',
            en: 'Language changed to English',
            es: 'Idioma cambiado a Español',
            ar: 'تم تغيير اللغة إلى العربية'
        });

        if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
            const currentField = this.registrationFields[this.currentFieldIndex];
            this.addBotMessage({
                [this.currentLanguage]: currentField.question[this.currentLanguage]
            });
        }
    }

    processUserResponse(response: string) {
        this.addUserMessage(response);

        if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
            const currentField = this.registrationFields[this.currentFieldIndex];

            if (currentField.options) {
                const normalizedResponse = this.normalizeResponse(response, currentField.name);
                if (normalizedResponse) {
                    currentField.value = normalizedResponse;
                } else {
                    this.addBotMessage({
                        fr: `Désolé, je n'ai pas compris. Veuillez choisir parmi: ${currentField.options.join(', ')}`,
                        en: `Sorry, I didn't understand. Please choose from: ${currentField.options.join(', ')}`,
                        es: `Lo siento, no entendí. Por favor elija entre: ${currentField.options.join(', ')}`,
                        ar: `عذرًا، لم أفهم. الرجاء الاختيار من بين: ${currentField.options.join('، ')}`
                    });

                    setTimeout(() => {
                        this.startListening();
                    }, 1000);
                    return;
                }
            } else {
                currentField.value = response.trim();
            }

            this.registrationForm.get(currentField.name).setValue(currentField.value);

            setTimeout(() => {
                this.nextQuestion();
            }, 500);
        }
    }

    normalizeResponse(response: string, fieldName: string): string | null {
        const lowerResponse = response.toLowerCase().trim();

        if (fieldName === 'languagePreference') {
            const langMap = {
                'fr': ['français', 'francais', 'french', 'فرنسي'],
                'en': ['anglais', 'english', 'إنجليزي'],
                'bilingual': ['bilingue', 'bilingual', 'bi-lingue', 'the two', 'les deux', 'ثنائي اللغة']
            };

            for (const [key, values] of Object.entries(langMap)) {
                if (values.some(val => lowerResponse.includes(val))) {
                    return key === 'fr' ? 'français' : (key === 'en' ? 'anglais' : 'bilingue');
                }
            }
        }
        else if (fieldName === 'languageLevel') {
            const levelMap = {
                'beginner': ['débutant', 'debutant', 'beginner', 'basic', 'basique', 'principiante', 'مبتدئ'],
                'intermediate': ['intermédiaire', 'intermediaire', 'intermediate', 'medio', 'متوسط'],
                'advanced': ['avancé', 'avance', 'advanced', 'expert', 'avanzado', 'متقدم']
            };

            for (const [key, values] of Object.entries(levelMap)) {
                if (values.some(val => lowerResponse.includes(val))) {
                    return key === 'beginner' ? 'débutant' : (key === 'intermediate' ? 'intermédiaire' : 'avancé');
                }
            }
        }

        return null;
    }

    nextQuestion() {
        this.currentFieldIndex++;

        if (this.currentFieldIndex < this.registrationFields.length) {
            const currentField = this.registrationFields[this.currentFieldIndex];
            this.addBotMessage({
                [this.currentLanguage]: currentField.question[this.currentLanguage]
            });
        } else {
            this.confirmRegistration();
        }
    }

    confirmRegistration() {
        const registrationData = {};
        this.registrationFields.forEach(field => {
            registrationData[field.name] = field.value;
        });

        const confirmMessages = {
            fr: `Je vais enregistrer vos informations:\n${this.registrationFields.map(f => `${f.question.fr.replace(' ?', '')}: ${f.value}`).join('\n')}\nEst-ce correct ? Dites "oui" pour confirmer ou "non" pour recommencer.`,
            en: `I'll register your information:\n${this.registrationFields.map(f => `${f.question.en.replace('?', '')}: ${f.value}`).join('\n')}\nIs this correct? Say "yes" to confirm or "no" to start over.`,
            es: `Registraré su información:\n${this.registrationFields.map(f => `${f.question.es.replace('?', '')}: ${f.value}`).join('\n')}\n¿Es correcto? Diga "sí" para confirmar o "no" para comenzar de nuevo.`,
            ar: `سأسجل معلوماتك:\n${this.registrationFields.map(f => `${f.question.ar.replace('؟', '')}: ${f.value}`).join('\n')}\nهل هذا صحيح؟ قل "نعم" للتأكيد أو "لا" للبدء من جديد.`
        };

        this.addBotMessage(confirmMessages);

        const SpeechRecognition = window['SpeechRecognition'] || window['webkitSpeechRecognition'];
        this.recognitionInstance = new SpeechRecognition();
        this.recognitionInstance.lang = this.getSpeechRecognitionLocale();
        this.recognitionInstance.continuous = false;
        this.recognitionInstance.interimResults = false;

        this.recognitionInstance.onresult = (event) => {
            const transcript = event.results[0][0].transcript.toLowerCase();
            this.isListening = false;

            const confirmationResponses = {
                fr: ['oui', 'ouais', 'correct', 'confirmer', 'ok', 'd\'accord'],
                en: ['yes', 'yeah', 'correct', 'confirm', 'ok', 'alright'],
                es: ['sí', 'si', 'correcto', 'confirmar', 'vale', 'de acuerdo'],
                ar: ['نعم', 'أجل', 'صحيح', 'تأكيد', 'موافق']
            };

            const negativeResponses = {
                fr: ['non', 'incorrect', 'recommencer', 'refaire'],
                en: ['no', 'incorrect', 'restart', 'redo'],
                es: ['no', 'incorrecto', 'reiniciar', 'rehacer'],
                ar: ['لا', 'غير صحيح', 'إعادة', 'البدء من جديد']
            };

            const isConfirmed = confirmationResponses[this.currentLanguage].some(word =>
                transcript.includes(word)
            );

            const isRejected = negativeResponses[this.currentLanguage].some(word =>
                transcript.includes(word)
            );

            this.addUserMessage(transcript);

            if (isConfirmed) {
                this.submitRegistration(registrationData);
            } else if (isRejected) {
                this.resetRegistration();
            } else {
                this.addBotMessage({
                    fr: 'Je n\'ai pas compris votre réponse. Veuillez dire "oui" pour confirmer ou "non" pour recommencer.',
                    en: 'I didn\'t understand your response. Please say "yes" to confirm or "no" to start over.',
                    es: 'No entendí su respuesta. Por favor diga "sí" para confirmar o "no" para comenzar de nuevo.',
                    ar: 'لم أفهم إجابتك. يرجى قول "نعم" للتأكيد أو "لا" للبدء من جديد.'
                });

                setTimeout(() => {
                    this.startListening();
                }, 1000);
            }
        };

        this.recognitionInstance.onerror = (event) => {
            this.isListening = false;
            this.addBotMessage({
                fr: 'Désolé, je n\'ai pas pu vous entendre. Veuillez réessayer.',
                en: 'Sorry, I couldn\'t hear you. Please try again.',
                es: 'Lo siento, no pude escucharte. Por favor, inténtalo de nuevo.',
                ar: 'عذرًا، لم أستطع سماعك. يرجى المحاولة مرة أخرى.'
            });
        };

        this.recognitionInstance.onend = () => {
            this.isListening = false;
        };

        setTimeout(() => {
            this.startListening();
        }, 1000);
    }

    submitRegistration(data: any) {
        this.processingRegistration = true;

        this.addBotMessage({
            fr: 'Enregistrement de vos informations en cours...',
            en: 'Registering your information...',
            es: 'Registrando su información...',
            ar: 'جاري تسجيل معلوماتك...'
        });

        this.inscriptionOpenService.save().subscribe(
            response => {
                this.processingRegistration = false;
                this.registrationCompleted = true;

                this.addBotMessage({
                    fr: 'Félicitations ! Votre inscription est réussie.',
                    en: 'Congratulations! Your registration is successful.',
                    es: '¡Felicidades! Su registro es exitoso.',
                    ar: 'تهانينا! تم تسجيلك بنجاح.'
                });
            },
            error => {
                this.processingRegistration = false;
                console.error('Registration error:', error);

                this.addBotMessage({
                    fr: 'Désolé, une erreur est survenue lors de l\'inscription. Veuillez réessayer plus tard.',
                    en: 'Sorry, an error occurred during registration. Please try again later.',
                    es: 'Lo sentimos, ocurrió un error durante el registro. Por favor, inténtelo de nuevo más tarde.',
                    ar: 'عذرًا، حدث خطأ أثناء التسجيل. يرجى المحاولة مرة أخرى لاحقًا.'
                });
            }
        );
    }

    resetRegistration() {
        this.currentFieldIndex = -1;
        this.registrationFields.forEach(field => {
            field.value = '';
        });
        this.registrationForm.reset();

        this.addBotMessage({
            fr: 'D\'accord, recommençons l\'inscription depuis le début.',
            en: 'Alright, let\'s restart the registration from the beginning.',
            es: 'De acuerdo, reiniciemos el registro desde el principio.',
            ar: 'حسنًا، دعنا نعيد التسجيل من البداية.'
        });

        setTimeout(() => {
            this.nextQuestion();
        }, 1000);
    }

    onManualInput(event: Event, fieldIndex: number) {
        const input = event.target as HTMLInputElement;
        const value = input.value;

        if (fieldIndex >= 0 && fieldIndex < this.registrationFields.length) {
            this.registrationFields[fieldIndex].value = value;
            this.registrationForm.get(this.registrationFields[fieldIndex].name).setValue(value);
        }
    }

    restartRegistration() {
        this.resetRegistration();
        this.registrationCompleted = false;
    }

    manuallyAdvance() {
        if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
            const currentField = this.registrationFields[this.currentFieldIndex];
            if (currentField.value.trim() === '') {
                this.messageService.add({
                    severity: 'warn',
                    summary: 'Champ requis',
                    detail: 'Veuillez répondre à la question avant de continuer.'
                });
                return;
            }
            this.nextQuestion();
        }
    }
}
// // voice-registration.component.ts
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { InscriptionOpenService } from "../../shared/service/open/profil/inscriptionOpen.service";
// import { MessageService } from 'primeng/api';
//
// interface ChatMessage {
//     text: string;
//     isBot: boolean;
//     timestamp: Date;
// }
//
// interface RegistrationField {
//     name: string;
//     question: {
//         fr: string;
//         en: string;
//         es: string;
//         ar: string;
//     };
//     validator: any;
//     value: string;
//     options?: string[];
// }
//
// @Component({
//     selector: 'app-voice-registration',
//     templateUrl: './voice-registration.component.html',
//     styleUrls: ['./voice-registration.component.scss'],
//     providers: [MessageService]
// })
// export class VoiceRegistrationComponent implements OnInit {
//     registrationForm: FormGroup;
//     chatMessages: ChatMessage[] = [];
//     isListening = false;
//     recognitionInstance: any;
//     currentLanguage = 'fr'; // Default language
//     availableLanguages = [
//         { code: 'fr', name: 'Français' },
//         { code: 'en', name: 'English' },
//         { code: 'es', name: 'Español' },
//         { code: 'ar', name: 'العربية' }
//     ];
//
//     registrationFields: RegistrationField[] = [
//         {
//             name: 'lastName',
//             question: {
//                 fr: 'Quel est votre nom de famille ?',
//                 en: 'What is your last name?',
//                 es: '¿Cuál es su apellido?',
//                 ar: 'ما هو اسم عائلتك؟'
//             },
//             validator: Validators.required,
//             value: ''
//         },
//         {
//             name: 'firstName',
//             question: {
//                 fr: 'Quel est votre prénom ?',
//                 en: 'What is your first name?',
//                 es: '¿Cuál es su nombre?',
//                 ar: 'ما هو اسمك الأول؟'
//             },
//             validator: Validators.required,
//             value: ''
//         },
//         {
//             name: 'email',
//             question: {
//                 fr: 'Quelle est votre adresse email ?',
//                 en: 'What is your email address?',
//                 es: '¿Cuál es su dirección de correo electrónico?',
//                 ar: 'ما هو بريدك الإلكتروني؟'
//             },
//             validator: [Validators.required, Validators.email],
//             value: ''
//         },
//         {
//             name: 'password',
//             question: {
//                 fr: 'Veuillez choisir un mot de passe ?',
//                 en: 'Please choose a password?',
//                 es: '¿Por favor elija una contraseña?',
//                 ar: 'الرجاء اختيار كلمة مرور؟'
//             },
//             validator: [Validators.required, Validators.minLength(8)],
//             value: ''
//         },
//         {
//             name: 'languagePreference',
//             question: {
//                 fr: 'Quelle langue préférez-vous ? Dites "français", "anglais" ou "bilingue".',
//                 en: 'Which language do you prefer? Say "French", "English", or "Bilingual".',
//                 es: '¿Qué idioma prefiere? Diga "francés", "inglés" o "bilingüe".',
//                 ar: 'ما هي اللغة التي تفضلها؟ قل "فرنسي" أو "إنجليزي" أو "ثنائي اللغة".'
//             },
//             validator: Validators.required,
//             value: '',
//             options: ['français', 'anglais', 'bilingue']
//         },
//         {
//             name: 'languageLevel',
//             question: {
//                 fr: 'Quel est votre niveau de langue ? Dites "débutant", "intermédiaire" ou "avancé".',
//                 en: 'What is your language level? Say "beginner", "intermediate", or "advanced".',
//                 es: '¿Cuál es su nivel de idioma? Diga "principiante", "intermedio" o "avanzado".',
//                 ar: 'ما هو مستواك اللغوي؟ قل "مبتدئ" أو "متوسط" أو "متقدم".'
//             },
//             validator: Validators.required,
//             value: '',
//             options: ['débutant', 'intermédiaire', 'avancé']
//         },
//         {
//             name: 'profession',
//             question: {
//                 fr: 'Quel est votre métier ?',
//                 en: 'What is your profession?',
//                 es: '¿Cuál es su profesión?',
//                 ar: 'ما هي مهنتك؟'
//             },
//             validator: Validators.required,
//             value: ''
//         },
//         {
//             name: 'experienceHours',
//             question: {
//                 fr: 'Combien d\'heures d\'expérience avez-vous dans ce métier ?',
//                 en: 'How many hours of experience do you have in this profession?',
//                 es: '¿Cuántas horas de experiencia tiene en esta profesión?',
//                 ar: 'كم عدد ساعات الخبرة لديك في هذه المهنة؟'
//             },
//             validator: [Validators.required, Validators.pattern('^[0-9]+$')],
//             value: ''
//         }
//     ];
//
//     currentFieldIndex = -1;
//     registrationCompleted = false;
//     processingRegistration = false;
//
//     constructor(
//         private fb: FormBuilder,
//         private inscriptionOpenService: InscriptionOpenService,
//         private messageService: MessageService
//     ) { }
//
//     ngOnInit() {
//         this.setupForm();
//         this.startConversation();
//
//         // Check if browser supports speech recognition
//         if (!('webkitSpeechRecognition' in window) && !('SpeechRecognition' in window)) {
//             this.addBotMessage({
//                 fr: 'Désolé, votre navigateur ne supporte pas la reconnaissance vocale. Veuillez utiliser Chrome, Edge ou Safari récent.',
//                 en: 'Sorry, your browser does not support speech recognition. Please use Chrome, Edge, or recent Safari.',
//                 es: 'Lo sentimos, su navegador no admite el reconocimiento de voz. Utilice Chrome, Edge o Safari reciente.',
//                 ar: 'عذرًا، متصفحك لا يدعم التعرف الصوتي. يرجى استخدام Chrome أو Edge أو Safari الحديث.'
//             });
//         }
//     }
//
//     setupForm() {
//         const formGroup = {};
//         this.registrationFields.forEach(field => {
//             formGroup[field.name] = ['', field.validator];
//         });
//         this.registrationForm = this.fb.group(formGroup);
//     }
//
//     startConversation() {
//         this.addBotMessage({
//             fr: 'Bonjour! Je suis votre assistant d\'inscription vocale. Vous pouvez changer la langue à tout moment. Pour commencer, cliquez sur le microphone.',
//             en: 'Hello! I am your voice registration assistant. You can change the language at any time. To begin, click on the microphone.',
//             es: '¡Hola! Soy su asistente de registro por voz. Puede cambiar el idioma en cualquier momento. Para comenzar, haga clic en el micrófono.',
//             ar: 'مرحبًا! أنا مساعد التسجيل الصوتي الخاص بك. يمكنك تغيير اللغة في أي وقت. للبدء، انقر على الميكروفون.'
//         });
//
//         // Wait a moment before asking the first question
//         setTimeout(() => {
//             this.nextQuestion();
//         }, 1000);
//     }
//
//     addBotMessage(messageByLang: {[key: string]: string}) {
//         const message = messageByLang[this.currentLanguage] || messageByLang['en'];
//         this.chatMessages.push({
//             text: message,
//             isBot: true,
//             timestamp: new Date()
//         });
//
//         // Text-to-speech for bot messages
//         if ('speechSynthesis' in window) {
//             const speech = new SpeechSynthesisUtterance(message);
//             speech.lang = this.getSpeechRecognitionLocale();
//             window.speechSynthesis.speak(speech);
//         }
//     }
//
//     addUserMessage(message: string) {
//         this.chatMessages.push({
//             text: message,
//             isBot: false,
//             timestamp: new Date()
//         });
//     }
//
//     getSpeechRecognitionLocale() {
//         const langMappings = {
//             'fr': 'fr-FR',
//             'en': 'en-US',
//             'es': 'es-ES',
//             'ar': 'ar-SA'
//         };
//         return langMappings[this.currentLanguage] || 'en-US';
//     }
//
//     startListening() {
//         if (this.isListening) return;
//
//         // Cancel any ongoing speech synthesis
//         if ('speechSynthesis' in window) {
//             window.speechSynthesis.cancel();
//         }
//
//         const SpeechRecognition = window['SpeechRecognition'] || window['webkitSpeechRecognition'];
//         this.recognitionInstance = new SpeechRecognition();
//         this.recognitionInstance.lang = this.getSpeechRecognitionLocale();
//         this.recognitionInstance.continuous = false;
//         this.recognitionInstance.interimResults = false;
//
//         this.isListening = true;
//
//         this.recognitionInstance.onresult = (event) => {
//             const transcript = event.results[0][0].transcript;
//             this.isListening = false;
//             this.processUserResponse(transcript);
//         };
//
//         this.recognitionInstance.onerror = (event) => {
//             console.error('Speech recognition error', event.error);
//             this.isListening = false;
//
//             this.addBotMessage({
//                 fr: 'Désolé, je n\'ai pas pu vous entendre. Veuillez réessayer.',
//                 en: 'Sorry, I couldn\'t hear you. Please try again.',
//                 es: 'Lo siento, no pude escucharte. Por favor, inténtalo de nuevo.',
//                 ar: 'عذرًا، لم أستطع سماعك. يرجى المحاولة مرة أخرى.'
//             });
//         };
//
//         this.recognitionInstance.onend = () => {
//             this.isListening = false;
//         };
//
//         this.recognitionInstance.start();
//     }
//
//     stopListening() {
//         if (this.recognitionInstance) {
//             this.recognitionInstance.stop();
//             this.isListening = false;
//         }
//     }
//
//     changeLanguage(langCode: string) {
//         this.currentLanguage = langCode;
//         this.addBotMessage({
//             fr: 'Langue changée en Français',
//             en: 'Language changed to English',
//             es: 'Idioma cambiado a Español',
//             ar: 'تم تغيير اللغة إلى العربية'
//         });
//
//         // Repeat the current question in the new language
//         if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
//             const currentField = this.registrationFields[this.currentFieldIndex];
//             this.addBotMessage({
//                 [this.currentLanguage]: currentField.question[this.currentLanguage]
//             });
//         }
//     }
//
//     processUserResponse(response: string) {
//         this.addUserMessage(response);
//
//         if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
//             const currentField = this.registrationFields[this.currentFieldIndex];
//
//             // For fields with predefined options, validate the response
//             if (currentField.options) {
//                 const normalizedResponse = this.normalizeResponse(response, currentField.name);
//                 if (normalizedResponse) {
//                     currentField.value = normalizedResponse;
//                 } else {
//                     // Response doesn't match any option, ask again
//                     this.addBotMessage({
//                         fr: `Désolé, je n'ai pas compris. Veuillez choisir parmi: ${currentField.options.join(', ')}`,
//                         en: `Sorry, I didn't understand. Please choose from: ${currentField.options.join(', ')}`,
//                         es: `Lo siento, no entendí. Por favor elija entre: ${currentField.options.join(', ')}`,
//                         ar: `عذرًا، لم أفهم. الرجاء الاختيار من بين: ${currentField.options.join('، ')}`
//                     });
//
//                     // Listen again for this field
//                     setTimeout(() => {
//                         this.startListening();
//                     }, 1000);
//                     return;
//                 }
//             } else {
//                 currentField.value = response.trim();
//             }
//
//             // Update form control value
//             this.registrationForm.get(currentField.name).setValue(currentField.value);
//
//             // Move to next question with slight delay
//             setTimeout(() => {
//                 this.nextQuestion();
//             }, 500);
//         }
//     }
//
//     normalizeResponse(response: string, fieldName: string): string | null {
//         const lowerResponse = response.toLowerCase().trim();
//
//         if (fieldName === 'languagePreference') {
//             const langMap = {
//                 'fr': ['français', 'francais', 'french'],
//                 'en': ['anglais', 'english'],
//                 'bilingual': ['bilingue', 'bilingual', 'bi-lingue', 'the two', 'les deux']
//             };
//
//             for (const [key, values] of Object.entries(langMap)) {
//                 if (values.some(val => lowerResponse.includes(val))) {
//                     return key === 'fr' ? 'français' : (key === 'en' ? 'anglais' : 'bilingue');
//                 }
//             }
//         }
//
//         else if (fieldName === 'languageLevel') {
//             const levelMap = {
//                 'beginner': ['débutant', 'debutant', 'beginner', 'basic', 'basique', 'principiante', 'مبتدئ'],
//                 'intermediate': ['intermédiaire', 'intermediaire', 'intermediate', 'medio', 'متوسط'],
//                 'advanced': ['avancé', 'avance', 'advanced', 'expert', 'avanzado', 'متقدم']
//             };
//
//             for (const [key, values] of Object.entries(levelMap)) {
//                 if (values.some(val => lowerResponse.includes(val))) {
//                     return key === 'beginner' ? 'débutant' : (key === 'intermediate' ? 'intermédiaire' : 'avancé');
//                 }
//             }
//         }
//
//         return null; // No match found
//     }
//
//     nextQuestion() {
//         this.currentFieldIndex++;
//
//         if (this.currentFieldIndex < this.registrationFields.length) {
//             const currentField = this.registrationFields[this.currentFieldIndex];
//             this.addBotMessage({
//                 [this.currentLanguage]: currentField.question[this.currentLanguage]
//             });
//         } else {
//             // All questions answered, confirm registration
//             this.confirmRegistration();
//         }
//     }
//
//     confirmRegistration() {
//         const registrationData = {};
//         this.registrationFields.forEach(field => {
//             registrationData[field.name] = field.value;
//         });
//
//         const confirmMessages = {
//             fr: `Je vais enregistrer vos informations:\n${this.registrationFields.map(f => `${f.question.fr.replace(' ?', '')}: ${f.value}`).join('\n')}\nEst-ce correct ? Dites "oui" pour confirmer ou "non" pour recommencer.`,
//             en: `I'll register your information:\n${this.registrationFields.map(f => `${f.question.en.replace('?', '')}: ${f.value}`).join('\n')}\nIs this correct? Say "yes" to confirm or "no" to start over.`,
//             es: `Registraré su información:\n${this.registrationFields.map(f => `${f.question.es.replace('?', '')}: ${f.value}`).join('\n')}\n¿Es correcto? Diga "sí" para confirmar o "no" para comenzar de nuevo.`,
//             ar: `سأسجل معلوماتك:\n${this.registrationFields.map(f => `${f.question.ar.replace('؟', '')}: ${f.value}`).join('\n')}\nهل هذا صحيح؟ قل "نعم" للتأكيد أو "لا" للبدء من جديد.`
//         };
//
//         this.addBotMessage(confirmMessages);
//
//         // Set up the next recognition for confirmation
//         const SpeechRecognition = window['SpeechRecognition'] || window['webkitSpeechRecognition'];
//         this.recognitionInstance = new SpeechRecognition();
//         this.recognitionInstance.lang = this.getSpeechRecognitionLocale();
//         this.recognitionInstance.continuous = false;
//         this.recognitionInstance.interimResults = false;
//
//         this.recognitionInstance.onresult = (event) => {
//             const transcript = event.results[0][0].transcript.toLowerCase();
//             this.isListening = false;
//
//             // Check for confirmation based on language
//             const confirmationResponses = {
//                 fr: ['oui', 'ouais', 'correct', 'confirmer', 'ok', 'd\'accord'],
//                 en: ['yes', 'yeah', 'correct', 'confirm', 'ok', 'alright'],
//                 es: ['sí', 'si', 'correcto', 'confirmar', 'vale', 'de acuerdo'],
//                 ar: ['نعم', 'أجل', 'صحيح', 'تأكيد', 'موافق']
//             };
//
//             const negativeResponses = {
//                 fr: ['non', 'incorrect', 'recommencer', 'refaire'],
//                 en: ['no', 'incorrect', 'restart', 'redo'],
//                 es: ['no', 'incorrecto', 'reiniciar', 'rehacer'],
//                 ar: ['لا', 'غير صحيح', 'إعادة', 'البدء من جديد']
//             };
//
//             const isConfirmed = confirmationResponses[this.currentLanguage].some(word =>
//                 transcript.includes(word)
//             );
//
//             const isRejected = negativeResponses[this.currentLanguage].some(word =>
//                 transcript.includes(word)
//             );
//
//             this.addUserMessage(transcript);
//
//             if (isConfirmed) {
//                 this.submitRegistration(registrationData);
//             } else if (isRejected) {
//                 // Reset and start over
//                 this.resetRegistration();
//             } else {
//                 // Unclear response
//                 this.addBotMessage({
//                     fr: 'Je n\'ai pas compris votre réponse. Veuillez dire "oui" pour confirmer ou "non" pour recommencer.',
//                     en: 'I didn\'t understand your response. Please say "yes" to confirm or "no" to start over.',
//                     es: 'No entendí su respuesta. Por favor diga "sí" para confirmar o "no" para comenzar de nuevo.',
//                     ar: 'لم أفهم إجابتك. يرجى قول "نعم" للتأكيد أو "لا" للبدء من جديد.'
//                 });
//
//                 // Wait and listen again
//                 setTimeout(() => {
//                     this.startListening();
//                 }, 1000);
//             }
//         };
//
//         this.recognitionInstance.onerror = (event) => {
//             this.isListening = false;
//             this.addBotMessage({
//                 fr: 'Désolé, je n\'ai pas pu vous entendre. Veuillez réessayer.',
//                 en: 'Sorry, I couldn\'t hear you. Please try again.',
//                 es: 'Lo siento, no pude escucharte. Por favor, inténtalo de nuevo.',
//                 ar: 'عذرًا، لم أستطع سماعك. يرجى المحاولة مرة أخرى.'
//             });
//         };
//
//         this.recognitionInstance.onend = () => {
//             this.isListening = false;
//         };
//
//         // Wait a moment before starting to listen
//         setTimeout(() => {
//             this.startListening();
//         }, 1000);
//     }
//
//     submitRegistration(data: any) {
//         this.processingRegistration = true;
//
//         this.addBotMessage({
//             fr: 'Enregistrement de vos informations en cours...',
//             en: 'Registering your information...',
//             es: 'Registrando su información...',
//             ar: 'جاري تسجيل معلوماتك...'
//         });
//
//         this.inscriptionOpenService.save().subscribe(
//             response => {
//                 this.processingRegistration = false;
//                 this.registrationCompleted = true;
//
//                 this.addBotMessage({
//                     fr: 'Félicitations ! Votre inscription est réussie.',
//                     en: 'Congratulations! Your registration is successful.',
//                     es: '¡Felicidades! Su registro es exitoso.',
//                     ar: 'تهانينا! تم تسجيلك بنجاح.'
//                 });
//             },
//             error => {
//                 this.processingRegistration = false;
//                 console.error('Registration error:', error);
//
//                 this.addBotMessage({
//                     fr: 'Désolé, une erreur est survenue lors de l\'inscription. Veuillez réessayer plus tard.',
//                     en: 'Sorry, an error occurred during registration. Please try again later.',
//                     es: 'Lo sentimos, ocurrió un error durante el registro. Por favor, inténtelo de nuevo más tarde.',
//                     ar: 'عذرًا، حدث خطأ أثناء التسجيل. يرجى المحاولة مرة أخرى لاحقًا.'
//                 });
//             }
//         );
//     }
//
//     resetRegistration() {
//         this.currentFieldIndex = -1;
//         this.registrationFields.forEach(field => {
//             field.value = '';
//         });
//         this.registrationForm.reset();
//
//         this.addBotMessage({
//             fr: 'D\'accord, recommençons l\'inscription depuis le début.',
//             en: 'Alright, let\'s restart the registration from the beginning.',
//             es: 'De acuerdo, reiniciemos el registro desde el principio.',
//             ar: 'حسنًا، دعنا نعيد التسجيل من البداية.'
//         });
//
//         // Start over with a slight delay
//         setTimeout(() => {
//             this.nextQuestion();
//         }, 1000);
//     }
//
//     // For manual keyboard input as fallback
//     onManualInput(event: Event, fieldIndex: number) {
//         const input = event.target as HTMLInputElement;
//         const value = input.value;
//
//         if (fieldIndex >= 0 && fieldIndex < this.registrationFields.length) {
//             this.registrationFields[fieldIndex].value = value;
//             this.registrationForm.get(this.registrationFields[fieldIndex].name).setValue(value);
//         }
//     }
//
//     // Start registration over with button
//     restartRegistration() {
//         this.resetRegistration();
//         this.registrationCompleted = false;
//     }
//
//     // For manually advancing to next question (accessibility)
//     manuallyAdvance() {
//         if (this.currentFieldIndex >= 0 && this.currentFieldIndex < this.registrationFields.length) {
//             const currentField = this.registrationFields[this.currentFieldIndex];
//             if (currentField.value.trim() === '') {
//                 this.messageService.add({
//                     severity: 'warn',
//                     summary: 'Champ requis',
//                     detail: 'Veuillez répondre à la question avant de continuer.'
//                 });
//                 return;
//             }
//             this.nextQuestion();
//         }
//     }
// }
