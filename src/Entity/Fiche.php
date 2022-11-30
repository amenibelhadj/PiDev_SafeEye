<?php

namespace App\Entity;

use App\Repository\FicheRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: FicheRepository::class)]
class Fiche
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $Nom_patient = null;

    #[ORM\Column(length: 255)]
    private ?string $Prenom_patient = null;

    #[ORM\Column(length: 255)]
    private ?string $Medecin = null;

    #[ORM\Column(length: 255)]
    private ?string $Maladie = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomPatient(): ?string
    {
        return $this->Nom_patient;
    }

    public function setNomPatient(string $Nom_patient): self
    {
        $this->Nom_patient = $Nom_patient;

        return $this;
    }

    public function getPrenomPatient(): ?string
    {
        return $this->Prenom_patient;
    }

    public function setPrenomPatient(string $Prenom_patient): self
    {
        $this->Prenom_patient = $Prenom_patient;

        return $this;
    }

    public function getMedecin(): ?string
    {
        return $this->Medecin;
    }

    public function setMedecin(string $Medecin): self
    {
        $this->Medecin = $Medecin;

        return $this;
    }

    public function getMaladie(): ?string
    {
        return $this->Maladie;
    }

    public function setMaladie(string $Maladie): self
    {
        $this->Maladie = $Maladie;

        return $this;
    }
}
