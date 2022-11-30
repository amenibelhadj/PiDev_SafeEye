<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: UserRepository::class)]
class User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $nom_user = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom_user = null;

    #[ORM\Column(length: 255)]
    private ?string $cin_user = null;

    #[ORM\Column(length: 255)]
    private ?string $email_user = null;

    #[ORM\Column(length: 255)]
    private ?string $role_user = null;

    #[ORM\Column(length: 255)]
    private ?string $mdp_user = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomUser(): ?string
    {
        return $this->nom_user;
    }

    public function setNomUser(string $nom_user): self
    {
        $this->nom_user = $nom_user;

        return $this;
    }

    public function getPrenomUser(): ?string
    {
        return $this->prenom_user;
    }

    public function setPrenomUser(string $prenom_user): self
    {
        $this->prenom_user = $prenom_user;

        return $this;
    }

    public function getCinUser(): ?string
    {
        return $this->cin_user;
    }

    public function setCinUser(string $cin_user): self
    {
        $this->cin_user = $cin_user;

        return $this;
    }

    public function getEmailUser(): ?string
    {
        return $this->email_user;
    }

    public function setEmailUser(string $email_user): self
    {
        $this->email_user = $email_user;

        return $this;
    }

    public function getRoleUser(): ?string
    {
        return $this->role_user;
    }

    public function setRoleUser(string $role_user): self
    {
        $this->role_user = $role_user;

        return $this;
    }

    public function getMdpUser(): ?string
    {
        return $this->mdp_user;
    }

    public function setMdpUser(string $mdp_user): self
    {
        $this->mdp_user = $mdp_user;

        return $this;
 
    }

    
    public function __toString()
{
    return (string)$this->getId();
}

}
