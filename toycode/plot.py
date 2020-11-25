import torch
import torchaudio
import matplotlib.pyplot as plt
from tqdm import tqdm
from glob import glob
filenames = glob('./total/*')
for idx, filename in tqdm(enumerate(filenames)):
    waveform, sample_rate = torchaudio.load(filename)

    # print("Shape of waveform: {}".format(waveform.size()))
    # print("Sample rate of waveform: {}".format(sample_rate))

    plt.figure()
    plt.plot(waveform.t().numpy())
    plt.savefig('./plotwave/{}.png'.format(idx))
    plt.close()

# read error in RIFF header not found