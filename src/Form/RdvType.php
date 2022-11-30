<?php

namespace App\Form;

use App\Entity\Rdv;
use App\Entity\Disponibilite;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

use Symfony\Component\Form\Extension\Core\Type\DateTimeType;


use Symfony\Bridge\Doctrine\Form\Type\EntityType;

use Symfony\Component\Form\Extension\Core\Type\DateType;

class RdvType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
           /* 
            ->add('nom_med',EntityType::class,
            ['class'=>Disponibilite::class,
            'choice_label'=>'nom_medd','multiple'=>false])

            
           ->add('heure', ChoiceType::class, [
                'choices'  => [
                    
                    '09:00' => '09:00',
                    '10:00' => '10:00',
                    '11:00' => '11:00',
                    '13:00' => '13:00',
                ],
            ]) 

           
           

        ->add('date', DateTimeType::class, [
                'date_widget' => 'single_text'
            ])

           */

           
          ->add('nom_med', ChoiceType::class, [
            'choices'  => [
                
                'Kousay' => 'Kousay',
                'Mohamed' => 'Mohamed',
                'Ameni' => 'Ameni',
                'Khairy' => 'Khairy',
            ],
        ])

            ->add('date')

            

          #  ->add('heure')
            
     
            


       
           ->add('prv', ChoiceType::class, [
                'choices'  => [
                    
                    'Oui' => 'oui',
                    'Non' => 'non',
                ],
                'label'=>'1ère visite : '
            ]) 

            #->add('id_user')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Rdv::class,
        ]);
    }
}
